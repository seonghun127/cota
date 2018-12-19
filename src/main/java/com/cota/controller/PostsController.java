package com.cota.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.cota.domain.Posts;
import com.cota.dto.PostsListDto;
import com.cota.dto.PostsSaveDto;
import com.cota.service.PostsService;
import com.cota.util.StringUtil;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class PostsController {
	
	public static final Logger logger = LoggerFactory.getLogger(PostsController.class);
	
	private PostsService postsService;
	
	// ------------------------------------------------------------------------------ //
	
	/**
	 * save new posts
	 * @param dto
	 * @return
	 */
	@CrossOrigin
	@PostMapping("/save")
	public ResponseEntity<?> savePosts(@RequestBody PostsSaveDto dto, UriComponentsBuilder ucBuilder) {
		
		logger.info("PostsSaveDto : " + dto);
		
		Long pNo = postsService.save(dto);
		
		logger.info("PostsCount is "+postsService.getCount());
		
		HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/posts/{pNo}").buildAndExpand(pNo).toUri());
		
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	// ------------------------------------------------------------------------------ //
	
	/**
	 * get one post (detail)
	 * @param pNo
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/posts/{pNo}")
    public ResponseEntity<?> getPost(@PathVariable("pNo") long pNo, Model model,
    		HttpServletRequest request) {
        
		logger.info("Fetching Posts with pNo {}", pNo);
		
		HttpSession session = request.getSession();
		Map<String, String> param = new HashMap<String, String>();
		param.put("pNo", Long.toString(pNo));
		param.put("uNo", StringUtil.nvl(session.getAttribute("uNo"), ""));
        
		Optional<Posts> posts = postsService.findById(pNo);
		
		boolean lCheck = postsService.getLikeCheck(param);
		boolean fCheck = postsService.getFollowCheck(param);
		boolean rCheck = postsService.getRepositoryCheck(param);
		
		model.addAttribute("posts", posts);
		model.addAttribute("lCheck", lCheck);
		model.addAttribute("fCheck", fCheck);
		model.addAttribute("rCheck", rCheck);
		
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

	// ------------------------------------------------------------------------------ //
	
	/**
	 * print post_list
	 * @return
	 */
	@CrossOrigin
	@PostMapping(value = "list/{category}/{rowNum}")
    public ResponseEntity<?> getPostList(@PathVariable("category") String category, 
    		@PathVariable("rowNum") int rowNum, HttpServletRequest request) {
        
		HttpSession session = request.getSession();
		Map<String, Object> param = new HashMap<String, Object>();
		
		param.put("category", StringUtil.nvl(category, "recent"));
		param.put("rowNum", rowNum);
		param.put("uNo", StringUtil.nvl(session.getAttribute("uNo"), ""));

		List<PostsListDto> list = postsService.findAll(param);
        
        return new ResponseEntity<List<PostsListDto>>(list, HttpStatus.OK);
    }
	
	// ------------------------------------------------------------------------------ //
}

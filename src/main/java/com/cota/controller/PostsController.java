package com.cota.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.cota.domain.Posts;
import com.cota.dto.PostsListDto;
import com.cota.dto.PostsSaveDto;
import com.cota.dto.PostsUpdateDto;
import com.cota.service.PostsService;

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
	public ResponseEntity<?> savePosts(@RequestBody PostsSaveDto dto,
	 UriComponentsBuilder ucBuilder) {
		
		logger.info("Saving Posts with PostsSaveDto : " + dto);
		
		Long pNo = postsService.save(dto);
		
		HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/posts/{pNo}").buildAndExpand(pNo).toUri());
		
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	// ------------------------------------------------------------------------------ //
	
	/**
	 * update posts
	 * @param pNo
	 * @param dto
	 * @param ucBuilder
	 * @return
	 */
	@PutMapping("/posts/{pNo}")
	public ResponseEntity<?> updateUser(@PathVariable("pNo") long pNo, 
		@RequestBody PostsUpdateDto dto, UriComponentsBuilder ucBuilder) {
        logger.info("Updating Posts with pNo {}", pNo);
 
        String pTitle = dto.getPTitle();
        String pContent = dto.getPContent();
        String pThumbnail = dto.getPThumbnail();
        
        postsService.updatePosts(pNo, pTitle, pContent, pThumbnail);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/posts/{pNo}").buildAndExpand(pNo).toUri());
       
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
	
	// ------------------------------------------------------------------------------ //
	
	/**
	 * delete posts
	 * @param pNo
	 * @return
	 */
	@DeleteMapping(value = "/posts/{pNo}")
    public ResponseEntity<?> deleteUser(@PathVariable("pNo") long pNo) {
        logger.info("Deleting Posts with pNo {}", pNo);
       
        postsService.deletePostsById(pNo);
        return new ResponseEntity<Posts>(HttpStatus.NO_CONTENT);
    }
	
	// ------------------------------------------------------------------------------ //
	
	/**
	 * get one post (detail)
	 * @param pNo
	 * @return
	 */
	@CrossOrigin
	@GetMapping(value = "/posts/{pNo}")
    public ResponseEntity<?> getPost(@PathVariable("pNo") long pNo, Model model) {
        
		logger.info("Fetching Posts with pNo {}", pNo);
		
		Map<String, String> param = new HashMap<String, String>();
		
		param.put("pNo", Long.toString(pNo));
		
		// retrieve post detail
		Optional<Posts> posts = postsService.findById(pNo);
		
		model.addAttribute("posts", posts);
		
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

	// ------------------------------------------------------------------------------ //
	
	/**
	 * print post_list
	 * @return
	 */
	@CrossOrigin
	@PostMapping(value = "list/{rowNum}")
	public ResponseEntity<?> getPostList(@PathVariable("rowNum") int rowNum) {
		
		logger.info("Retrieving All Posts!");

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("rowNum", rowNum);

		List<PostsListDto> list = postsService.findAll(param);
        
        return new ResponseEntity<List<PostsListDto>>(list, HttpStatus.OK);
    }
	
	// ------------------------------------------------------------------------------ //
}

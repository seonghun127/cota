package com.cota.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.cota.domain.Posts;
import com.cota.dto.posts.*;
import com.cota.service.posts.PostsService;

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
	@PostMapping("/posts")
	public ResponseEntity<?> savePosts(@RequestBody PostsSaveDto dto, Model model) {
		
		logger.info("Saving Posts with PostsSaveDto : " + dto);
		
		Long pNo = postsService.savePost(dto);

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("check", "one_post_details");
		param.put("pNo", pNo);

		dto.setParam(param);

		// after saving, retrieve that post
		List<PostsListDto> post = postsService.findPost(dto);

		model.addAttribute("post", post);
		
		return new ResponseEntity<>(post, HttpStatus.CREATED);
	}
	
	// ------------------------------------------------------------------------------ //
	
	/**
	 * update posts
	 * @param pNo
	 * @param dto
	 * @return
	 */
	@CrossOrigin
	@PutMapping("/posts/{pNo}")
	public ResponseEntity<?> updatePosts(@PathVariable("pNo") Long pNo, 
		@RequestBody PostsUpdateDto dto, Model model) {
		logger.info("Updating Posts with pNo {}", pNo);
		
		dto.setPNo(pNo);
		Posts findPost = postsService.findByPNo(dto);

		dto.setCreatedDate(findPost.getCreatedDate());

		Long _pNo = postsService.updatePost(dto);

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("check", "one_post_details");
        param.put("pNo", _pNo);
        
        dto.setParam(param);

        // after saving, retrieve that post
		List<PostsListDto> post = postsService.findPost(dto);

		model.addAttribute("post", post);
		
		return new ResponseEntity<>(post, HttpStatus.CREATED);
    }
	
	// ------------------------------------------------------------------------------ //
	
	/**
	 * delete posts
	 * @param pNo
	 * @return
	 */
	@CrossOrigin
	@DeleteMapping("/posts/{pNo}")
    public ResponseEntity<?> deletePosts(@PathVariable("pNo") long pNo) {
        logger.info("Deleting Posts with pNo {}", pNo);
		
		PostsDeleteDto dto = new PostsDeleteDto();
		dto.setPNo(pNo);

		postsService.deletePostsById(dto);
		
        return new ResponseEntity<Posts>(HttpStatus.NO_CONTENT);
    }

	// ------------------------------------------------------------------------------ //
	
	/**
	 * print post_list
	 * @return
	 */
	@CrossOrigin
	@GetMapping("/posts/{rowNum}")
	public ResponseEntity<?> getPostList(@PathVariable("rowNum") int rowNum) {
		
		logger.info("Retrieving All Posts!");

		PostsListDto dto = new PostsListDto();

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("rowNum", rowNum);
		param.put("check", "all_post_details");

		dto.setParam(param);

		List<PostsListDto> list = postsService.findPost(dto);
        
        return new ResponseEntity<List<PostsListDto>>(list, HttpStatus.OK);
    }
	
	// ------------------------------------------------------------------------------ //
}

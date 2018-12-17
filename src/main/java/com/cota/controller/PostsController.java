package com.cota.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.cota.domain.Posts;
import com.cota.dto.PostsSaveDto;
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
	public ResponseEntity<?> savePosts(@RequestBody PostsSaveDto dto, UriComponentsBuilder ucBuilder) {
		
		logger.info("PostsSaveDto : " + dto);
		
		Long pNo = postsService.save(dto);
		
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
    public ResponseEntity<?> getPost(@PathVariable("pNo") long pNo) {
        
		logger.info("Fetching Posts with pNo {}", pNo);
        
		Optional<Posts> posts = postsService.findById(pNo);
        
        return new ResponseEntity<Optional<Posts>>(posts, HttpStatus.OK);
    }
	
	// ------------------------------------------------------------------------------ //
	
	
	@CrossOrigin
	@PostMapping(value = "list")
    public ResponseEntity<?> getPostList() {
        
		List<Posts> list = postsService.findAll();
        
        return new ResponseEntity<List<Posts>>(list, HttpStatus.OK);
    }
}

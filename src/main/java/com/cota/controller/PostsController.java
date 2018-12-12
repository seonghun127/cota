package com.cota.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cota.dto.PostsSaveDto;
import com.cota.service.PostsService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class PostsController {
	
	private PostsService postsService;
	
	/* @GetMapping("/")
	public String index() {
		return "index";
	} */
	
	@CrossOrigin
	@PostMapping("/save")
	public Long savePosts(@RequestBody PostsSaveDto dto) {
		return postsService.save(dto);
	}

}

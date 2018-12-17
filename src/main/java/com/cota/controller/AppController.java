package com.cota.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cota.domain.Posts;
import com.cota.dto.PostsSaveDto;
import com.cota.repository.PostsRepository;

@Controller
public class AppController {
	
	@Autowired
	PostsRepository postsRepository;
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@RequestMapping("/partials/{page}")
    String partialHandler(@PathVariable("page") final String page) {
        return page;
    }
}

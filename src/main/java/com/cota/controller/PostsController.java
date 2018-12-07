package com.cota.controller;

import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cota.dto.PostsSaveDto;
import com.cota.service.PostsService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class PostsController {
	
	private PostsService postsService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@PostMapping("/save")
	public Long savePosts(@RequestBody PostsSaveDto dto) {
		return postsService.save(dto);
	}
	
	@PostMapping("/insertImage")
    private String boardInsertProc(@RequestPart MultipartFile files) throws Exception{
        
        String fileName = files.getOriginalFilename(); 
        String fileNameExtension = FilenameUtils.getExtension(fileName).toLowerCase(); 
        File destinationFile; 
        String destinationFileName; 
        String fileUrl = "/home/ec2-user/app/img/";
        
        do { 
            destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + fileNameExtension;
            String file = fileUrl + destinationFileName;
            destinationFile = new File(file); 
        } while (destinationFile.exists()); 
        
        destinationFile.getParentFile().mkdirs(); 
        files.transferTo(destinationFile);

        return "file";
    }
}

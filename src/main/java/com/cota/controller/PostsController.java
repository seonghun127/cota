package com.cota.controller;

import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cota.dto.PostsSaveDto;
import com.cota.service.PostsService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
	
	@CrossOrigin
	@PostMapping("/insertImage")
    private String boardInsertProc(@RequestPart MultipartFile files) throws Exception{
		
        String fileName = files.getOriginalFilename(); 
        String fileNameExtension = FilenameUtils.getExtension(fileName).toLowerCase(); 
        File destinationFile; 
        String destinationFileName; 
        String file;
        String fileUrl = "/home/ec2-user/app/img/";
        
        do { 
            destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + fileNameExtension;
            file = fileUrl + destinationFileName;
            destinationFile = new File(file); 
        } while (destinationFile.exists()); 
        
        destinationFile.getParentFile().mkdirs(); 
        files.transferTo(destinationFile);

        return file;
    }
	
	@CrossOrigin
	@PostMapping("/{fileUri}")
	private String getImage(@PathVariable String fileUri) {
		String ec2 = "http://ec2-52-78-219-93.ap-northeast-2.compute.amazonaws.com:3001";
		
		return ec2 + fileUri;
	}
	
	@NoArgsConstructor
    @Data
    private static class UploadAttachmentResponse {

        private String fileName;

        private long fileSize;

        private String fileContentType;

        private String attachmentUrl;
    }
}

package com.cota.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	@PostMapping(value = "/insertImage", produces = MediaType.IMAGE_JPEG_VALUE)
    private ResponseEntity<byte[]> getImage(@RequestPart MultipartFile files) throws Exception{
		
        String fileName = files.getOriginalFilename(); 
        String fileNameExtension = FilenameUtils.getExtension(fileName).toLowerCase(); 
        File destinationFile; 
        String destinationFileName; 
        String file;
        String fileUrl = "/static/images/";
        
        do { 
            destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + fileNameExtension;
            file = fileUrl + destinationFileName;
            destinationFile = new File(file); 
        } while (destinationFile.exists()); 
        
        destinationFile.getParentFile().mkdirs(); 
        files.transferTo(destinationFile);

        ClassPathResource imgFile = new ClassPathResource(file);
        byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes);
    }
	
	@GetMapping(value = "/sid", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage() throws IOException {

        ClassPathResource imgFile = new ClassPathResource("/static/images/cat.jpg");
        byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes);
    }
	
	/*
	@CrossOrigin
	@PostMapping("/insertImage")
    private String boardInsertProc(@RequestPart MultipartFile files) throws Exception{
		
        String fileName = files.getOriginalFilename(); 
        String fileNameExtension = FilenameUtils.getExtension(fileName).toLowerCase(); 
        File destinationFile; 
        String destinationFileName; 
        String file;
        String fileUrl = "/static/images/";
        
        do { 
            destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + fileNameExtension;
            file = fileUrl + destinationFileName;
            destinationFile = new File(file); 
        } while (destinationFile.exists()); 
        
        destinationFile.getParentFile().mkdirs(); 
        files.transferTo(destinationFile);

        return file;
    } */
	
	
	
	@NoArgsConstructor
    @Data
    private static class UploadAttachmentResponse {

        private String fileName;

        private long fileSize;

        private String fileContentType;

        private String attachmentUrl;
    }
}

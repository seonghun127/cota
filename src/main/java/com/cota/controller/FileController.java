package com.cota.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cota.util.MediaUtils;
import com.cota.util.UploadFileUtils;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class FileController {
	
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);
	
	// 로컬 경로
	//private static final String UPLOAD_PATH = 
	//		"/Users/gimseonghun/Documents/springboot/cota/src/main/resources/static/images";
	
	// 운영 서버 경로
	//private static final String UPLOAD_PATH = "/home/ec2-user/cota/travis/build/src/main/resources/static/images";
	private static final String UPLOAD_PATH = "/home/ec2-user/cota/img/uploadedFiles";
	
	
    // ------------------------------------------------------------------------------ //
    
    /**
     * 파일 업로드 이벤트 처리 메소드 (original)
     * @param file
     * @return
     * @throws Exception
     */
    @CrossOrigin
	@PostMapping(value="/upload", produces = "text/plain;charset=UTF-8")
	public ResponseEntity<String> uploadImage(MultipartFile file) throws Exception {
		
		logger.info("uploadImage starts");
		logger.info("originalName:" + file.getOriginalFilename());
		logger.info("size:" + file.getSize());
		logger.info("contentType:" + file.getContentType());
		
		//HttpStatus.CREATED : 리소스가 정상적으로 생성되었다는 상태코드.
		//return new ResponseEntity<>(file.getOriginalFilename(), HttpStatus.CREATED);
		return new ResponseEntity<>(
				UploadFileUtils.uploadFile(UPLOAD_PATH,
												file.getOriginalFilename(),
												file.getBytes()),
						HttpStatus.CREATED);
	}
	
	// ------------------------------------------------------------------------------ //
    
    /**
     * 저장된 파일(사진)을 화면에 보여주기 (original)
     * @param fileName
     * @return
     * @throws Exception
     */
  	@GetMapping("displayFile")
  	public ResponseEntity<byte[]> displayFile(String fileName) throws Exception {
  		
  		InputStream in = null;
  		ResponseEntity<byte[]> entity = null;
  		
  		logger.info("File name: " + fileName);
  		
  		try {
  			String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
  			
  			MediaType mType = MediaUtils.getMediaType(formatName);
  			
  			HttpHeaders headers = new HttpHeaders();
  			
  			in = new FileInputStream(UPLOAD_PATH + fileName);
  			
  			if(mType != null) {
  				headers.setContentType(mType);
  			}else {
  				fileName = fileName.substring(fileName.indexOf("_")+1);
  				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
  				headers.add("Content-Disposition", "attachment; filename=\"" 
  							+ new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");
  			}// else
  			
  			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
  			
  		} catch(Exception e) {
  			e.printStackTrace();
  			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
  		} finally {
  			in.close();
  		}
  		
  		return entity;
  	}// displayFile
  	
  	// ------------------------------------------------------------------------------ //
  	
  	/**
  	 * multipartFile로 이미지를 받고 썸네일 이미지로 변환 후 리턴 메소드 (수정)
  	 * @param file
  	 * @return
  	 * @throws Exception
  	 */
  	@PostMapping(value = "displayFile2", produces = "text/plain;charset=UTF-8")
  	public ResponseEntity<byte[]> displayFile2(MultipartFile file) throws Exception {
  		
  		InputStream in = null;
  		ResponseEntity<byte[]> entity = null;
  		String fileName;
  		
  		try {
  			fileName = UploadFileUtils.uploadFile(UPLOAD_PATH,
					file.getOriginalFilename(),
					file.getBytes());
  			
  			logger.info("File name: " + fileName);
  			
  			String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
  			
  			MediaType mType = MediaUtils.getMediaType(formatName);
  			
  			HttpHeaders headers = new HttpHeaders();
  			
  			in = new FileInputStream(UPLOAD_PATH + fileName);
  			
  			if(mType != null) {
  				headers.setContentType(mType);
  			}else {
  				fileName = fileName.substring(fileName.indexOf("_")+1);
  				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
  				headers.add("Content-Disposition", "attachment; filename=\"" 
  							+ new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");
  			}// else
  			
  			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
  			
  		} catch(Exception e) {
  			e.printStackTrace();
  			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
  		} finally {
  			in.close();
  		}
  		
  		return entity;
  	}
  	
  	// ------------------------------------------------------------------------------ //
	
}

package com.cota.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cota.domain.Comments;
import com.cota.dto.comments.*;
import com.cota.service.comments.CommentsService;

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

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class CommentsController{

	public static final Logger logger = LoggerFactory.getLogger(CommentsController.class);
	
	CommentsService commnetsService;

    // ------------------------------------------------------------------------------ //
	
	/**
	 * get comments list
	 */
	@CrossOrigin
	@GetMapping("/comments/{pNo}/{rowNum}")
	public ResponseEntity<?> getCommentList (@PathVariable("pNo") Long pNo, 
		@PathVariable("rowNum") int rowNum){
		
		logger.info("Retrieving comments!");

		CommentsListDto dto = new CommentsListDto();

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("check", "all_comments_details");
		param.put("pNo", pNo);
		param.put("rowNum", rowNum);

		dto.setParam(param);

		List<CommentsListDto> list = commnetsService.findComment(dto);

		return new ResponseEntity<List<CommentsListDto>>(list, HttpStatus.OK);
	}

	// ------------------------------------------------------------------------------ //
	
	/**
	 * delete comments
	 * @param cNo
	 * @return
	 */
	@CrossOrigin
	@DeleteMapping("/comments/{cNo}")
    public ResponseEntity<?> deleteComments(@PathVariable("cNo") long cNo) {
		logger.info("Deleting Comments with cNo {}", cNo);
		
		CommentsDeleteDto dto = new CommentsDeleteDto();

		dto.setCNo(cNo);
       
		commnetsService.deleteCommentsById(dto);
		
        return new ResponseEntity<Comments>(HttpStatus.NO_CONTENT);
	}

	// ------------------------------------------------------------------------------ //

	/**
	 * update comments
	 * @param cNo
	 * @param dto
	 * @return
	 */
	@CrossOrigin
	@PutMapping("/comments/{cNo}")
	public ResponseEntity<?> updateComments(@PathVariable("cNo") long cNo, 
		@RequestBody CommentsUpdateDto dto){
		
		logger.info("Updating comments with cNo {}", cNo);
		
		dto.setCNo(cNo);

		Comments findComment = commnetsService.findByCNo(dto);

		dto.setCreatedDate(findComment.getCreatedDate());

		commnetsService.updateComments(dto);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	// ------------------------------------------------------------------------------ //

	/**
	 * save comments
	 * @param dto
	 * @return 
	 */
	@CrossOrigin
	@PostMapping("/comments")
	public ResponseEntity<?> saveComments(@RequestBody CommentsSaveDto dto, Model model){
		logger.info("Saving Comments with CommentsSaveDto : ", dto);
		
		Long cNo = commnetsService.saveComments(dto);

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("check", "one_comment_details");
		param.put("cNo", cNo);

		dto.setParam(param);

		List<CommentsListDto> comment = commnetsService.findComment(dto);
		
		model.addAttribute("comment", comment);

		return new ResponseEntity<>(comment, HttpStatus.CREATED);
	}

	// ------------------------------------------------------------------------------ //
}
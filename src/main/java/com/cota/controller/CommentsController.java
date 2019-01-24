package com.cota.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.cota.domain.Comments;
import com.cota.dto.CommentsListDto;
import com.cota.dto.CommentsSaveDto;
import com.cota.dto.CommentsUpdateDto;
import com.cota.service.CommentsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
		@PathVariable("rowNum") int rowNum, HttpServletRequest request){
		
		logger.info("Retrieving comments!");
		//HttpSession session = request.getSession();

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("pNo", pNo);
		param.put("rowNum", rowNum);

		List<CommentsListDto> list = commnetsService.findAll(param);

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
       
        commnetsService.deleteCommentsById(cNo);
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
	public ResponseEntity<?> saveComments(@RequestBody CommentsSaveDto dto){
		logger.info("Saving Posts with CommentsSaveDto : ", dto);
		
		commnetsService.saveComments(dto);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	// ------------------------------------------------------------------------------ //
}
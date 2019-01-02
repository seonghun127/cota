package com.cota.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.github.scribejava.core.model.OAuth2AccessToken;

@RestController
public class LoginController {
	
	/* NaverLoginBO */
	@Autowired
	private NaverLoginBO naverLoginBO;
	
	private String apiResult = null;
	
	

	//로그인 첫 화면 요청 메소드
	@CrossOrigin
	@RequestMapping(value = "/naverLogin", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<?> login(Model model, HttpSession session) {
		
		/* 네이버아이디로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationUrl메소드 호출 */
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
		
		//https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=sE***************&
		//redirect_uri=http%3A%2F%2F211.63.89.90%3A8090%2Flogin_project%2Fcallback&state=e68c269c-5ba9-4c31-85da-54c16c658125
		System.out.println("네이버:" + naverAuthUrl);
		
		//네이버 
		model.addAttribute("url", naverAuthUrl);

		/* 생성한 인증 URL을 View로 전달 */
		return new ResponseEntity<>(model, HttpStatus.OK);
	}

	//네이버 로그인 성공시 callback호출 메소드
	@CrossOrigin
	@RequestMapping(value = "/callback", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<?> callback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session
			,UriComponentsBuilder ucBuilder)
			throws IOException {
		System.out.println("여기는 callback");
		OAuth2AccessToken oauthToken;
        oauthToken = naverLoginBO.getAccessToken(session, code, state);
        //로그인 사용자 정보를 읽어온다.
	    apiResult = naverLoginBO.getUserProfile(oauthToken);
		model.addAttribute("result", apiResult);
		
		HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/list/recent/0").buildAndExpand().toUri());

		return new ResponseEntity<>(headers, HttpStatus.OK);
	}
}

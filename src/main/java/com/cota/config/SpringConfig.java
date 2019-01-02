package com.cota.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cota.controller.NaverLoginBO;

@Configuration
//@EnableWebMvc
public class SpringConfig{

	@Bean
	public NaverLoginBO naverLoginBO() {
		return new NaverLoginBO();
	}
}

package com.cota;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing	// execute JPA Auditing 
public class CotaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CotaApplication.class, args);
	}
}

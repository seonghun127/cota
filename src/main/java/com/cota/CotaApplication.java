package com.cota;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing	// execute JPA Auditing 
@MapperScan(value= {"com.cota.mapper"})
public class CotaApplication {
	
	public static final String APPLICATION_LOCATIONS = "spring.config.location="
            + "classpath:application.yml,"
            + "/cota/config/cota/real-application.yml";

	public static void main(String[] args) {
		
		new SpringApplicationBuilder(CotaApplication.class)
        .properties(APPLICATION_LOCATIONS)
        .run(args);
	}

}

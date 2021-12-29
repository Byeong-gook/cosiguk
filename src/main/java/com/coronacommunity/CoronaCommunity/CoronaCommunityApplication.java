package com.coronacommunity.CoronaCommunity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@EnableScheduling
@EnableJpaAuditing
@SpringBootApplication
public class CoronaCommunityApplication {
	public static void main(String[] args) {
		SpringApplication.run(CoronaCommunityApplication.class, args);
	}

}

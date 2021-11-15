package com.coronacommunity.CoronaCommunity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;



@EnableJpaAuditing
@SpringBootApplication
public class CoronaCommunityApplication {
	public static void main(String[] args) {
		SpringApplication.run(CoronaCommunityApplication.class, args);
	}

}

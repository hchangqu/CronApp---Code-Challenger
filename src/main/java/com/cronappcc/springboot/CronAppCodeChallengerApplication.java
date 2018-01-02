package com.cronappcc.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.cronappcc.springboot.configuration.JpaConfiguration;


@Import(JpaConfiguration.class)
@SpringBootApplication(scanBasePackages={"com.cronappcc.springboot"})// same as @Configuration @EnableAutoConfiguration @ComponentScan
public class CronAppCodeChallengerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CronAppCodeChallengerApplication.class, args);
	}
}

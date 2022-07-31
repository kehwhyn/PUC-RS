package com.bcopstein.CtrlCorredor;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = { "com.bcopstein" })
@ComponentScan(basePackages = { "com.bcopstein" })
@EnableJpaRepositories(basePackages = { "com.bcopstein" })
public class CtrlCorredorApplication {
	public static void main(String[] args) {
		SpringApplication.run(CtrlCorredorApplication.class, args);
	}
}

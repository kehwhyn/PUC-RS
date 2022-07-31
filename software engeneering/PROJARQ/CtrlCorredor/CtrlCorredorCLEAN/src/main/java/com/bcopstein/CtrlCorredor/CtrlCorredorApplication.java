package com.bcopstein.CtrlCorredor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages={ "com.bcopstein" })
@ComponentScan(basePackages = { "com.bcopstein" })
@EntityScan(basePackages = { "com.bcopstein" })
public class CtrlCorredorApplication {
	public static void main(String[] args) {
		SpringApplication.run(CtrlCorredorApplication.class, args);
	}
}

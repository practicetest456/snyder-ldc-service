package com.snyder.ldc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.snyder.ldc" })
public class SnyderLdcApplication {

	public static void main(String[] args) {
		SpringApplication.run(SnyderLdcApplication.class, args);
	}
}

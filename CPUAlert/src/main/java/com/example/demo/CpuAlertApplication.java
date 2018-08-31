package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan
@EntityScan("com.example.beans")
@EnableJpaRepositories("com.example.demo.data")
public class CpuAlertApplication {

	public static void main(String[] args) {
		SpringApplication.run(CpuAlertApplication.class, args);
	}
}

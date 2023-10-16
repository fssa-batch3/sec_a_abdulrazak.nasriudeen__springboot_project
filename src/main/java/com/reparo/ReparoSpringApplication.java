package com.reparo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ReparoSpringApplication {
	public static void main(String[] args) {
		             SpringApplication.run(ReparoSpringApplication.class, args);
	}
}

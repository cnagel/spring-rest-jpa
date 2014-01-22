package com.surpreso.spring_rest_jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Import;




/**
 * A simple "Hello world" application using Spring Boot.
 * 
 * @author Christoph Nagel
 */
@Import(DefaultConfig.class)
public class HelloWorldApplication {

	public static void main(String... args) {
		SpringApplication.run(HelloWorldApplication.class, args);
	}

}

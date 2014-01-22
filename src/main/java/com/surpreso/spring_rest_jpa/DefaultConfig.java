package com.surpreso.spring_rest_jpa;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * The default configuration loading the Spring Boot context.
 * 
 * @author Christoph Nagel
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan("com.surpreso.spring_rest_jpa")
public class DefaultConfig {

}
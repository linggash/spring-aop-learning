package com.linggash.spring_aop_learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class SpringAopLearningApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAopLearningApplication.class, args);
	}

}

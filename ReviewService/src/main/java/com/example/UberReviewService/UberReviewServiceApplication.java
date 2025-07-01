package com.example.UberReviewService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing //@EnableJpaAuditing is an annotation in Spring Data JPA used to enable auditing capabilities in your application. When you apply this annotation, it allows Spring Data to automatically populate audit-related fields like createdDate, lastModifiedDate, createdBy, and lastModifiedBy in your entity classes.
public class UberReviewServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UberReviewServiceApplication.class, args);
	}

}

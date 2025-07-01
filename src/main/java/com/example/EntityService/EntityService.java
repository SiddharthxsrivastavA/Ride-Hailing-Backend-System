package com.example.EntityService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EntityService {

    public static void main(String[] args) {
        SpringApplication.run(EntityService.class, args);
    }

}

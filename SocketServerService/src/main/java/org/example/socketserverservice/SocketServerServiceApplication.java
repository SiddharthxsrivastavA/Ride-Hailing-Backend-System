package org.example.socketserverservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EntityScan("com.example.EntityService.models")
public class SocketServerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocketServerServiceApplication.class, args);
    }

}

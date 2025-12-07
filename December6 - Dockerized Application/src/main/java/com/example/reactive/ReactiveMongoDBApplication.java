package com.example.reactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@SpringBootApplication
public class ReactiveMongoDBApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveMongoDBApplication.class, args);
    }
}

package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import io.r2dbc.spi.ConnectionFactory;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;

import io.r2dbc.spi.ConnectionFactory;

@SpringBootApplication
public class WebFluxDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebFluxDemoApplication.class, args);
	}
	@Bean
	ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {
	    var initializer = new ConnectionFactoryInitializer();
	    initializer.setConnectionFactory(connectionFactory);
	    initializer.setDatabasePopulator(
	        new ResourceDatabasePopulator(new ClassPathResource("schema.sql"))
	    );
	    return initializer;
	}


}

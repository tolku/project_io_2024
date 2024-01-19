package com.fodapi.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication (exclude = {DataSourceAutoConfiguration.class }, scanBasePackages = {"com.fodapi"})
public class FodapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FodapiApplication.class, args);

	}

}

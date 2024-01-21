package com.fodapi.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication ( scanBasePackages = {"com.fodapi","com.fodapi.repositories"})
@EnableJpaRepositories("com.fodapi.repositories")
@EntityScan("com.fodapi.models.*")
public class FodapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FodapiApplication.class, args);

	}

}

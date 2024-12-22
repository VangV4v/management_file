package com.vang.management_file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(basePackages = {"com.vang.main"})
@EnableJpaRepositories(basePackages = {"com.vang.main.repository"})
@EnableTransactionManagement
@EntityScan(basePackages = {"com.vang.main.entities"})
public class ManagementFileApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagementFileApplication.class, args);
	}

}

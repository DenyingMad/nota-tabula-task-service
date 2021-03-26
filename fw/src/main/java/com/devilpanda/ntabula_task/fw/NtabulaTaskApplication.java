package com.devilpanda.ntabula_task.fw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.devilpanda.ntabula_task")
@EnableJpaRepositories(basePackages = "com.devilpanda.ntabula_task.adapter.jpa")
@EntityScan(basePackages = "com.devilpanda.ntabula_task.domain")
public class NtabulaTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(NtabulaTaskApplication.class, args);
	}

}

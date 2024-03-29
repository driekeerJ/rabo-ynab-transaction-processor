package com.expenses.expensesentrance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class ExpensesEntranceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpensesEntranceApplication.class, args);
		log.info("Started application");
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}

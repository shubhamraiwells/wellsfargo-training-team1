package com.banking.teamone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication
public class BankingSystemApplication {
	public static void main(String[] args) {
		SpringApplication.run(BankingSystemApplication.class, args);
		System.out.println("Application Running");
	}
}

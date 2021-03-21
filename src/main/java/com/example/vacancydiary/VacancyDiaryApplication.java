package com.example.vacancydiary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class VacancyDiaryApplication {

	public static void main(String[] args) {
		SpringApplication.run(VacancyDiaryApplication.class, args);
	}

}

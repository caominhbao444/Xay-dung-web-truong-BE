package com.project.trainingteam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TrainingTeamApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainingTeamApplication.class, args);
		System.out.println("SERVER IS RUNNINGGGGG !!!");
	}
}

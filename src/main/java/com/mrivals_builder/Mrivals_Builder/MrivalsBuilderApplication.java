package com.mrivals_builder.Mrivals_Builder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MrivalsBuilderApplication {

	public static void main(String[] args) {
		SpringApplication.run(MrivalsBuilderApplication.class, args);
	}

}

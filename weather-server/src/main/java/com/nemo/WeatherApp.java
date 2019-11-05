package com.nemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
/**
 * Author: D,Gurov
 * SpringBoot Application class
 */
@SpringBootApplication(scanBasePackages = "com.nemo.*")
@EnableScheduling
public class WeatherApp {

	public static void main(String[] args) {
		SpringApplication.run(WeatherApp.class, args);
	}
}

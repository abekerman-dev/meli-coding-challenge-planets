package com.mercadolibre.codingchallenge.main.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mercadolibre.codingchallenge.logic.WeatherForecastGenerator;
import com.mercadolibre.codingchallenge.web.controller.WeatherController;

@SpringBootApplication(scanBasePackageClasses = { WeatherForecastGenerator.class, WeatherController.class })
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}

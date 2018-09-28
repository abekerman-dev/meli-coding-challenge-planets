package com.mercadolibre.codingchallenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.codingchallenge.logic.WeatherForecastGenerator;
import com.mercadolibre.codingchallenge.pojo.PronosticoDiario;

/**
 * Único Controller de la aplicación Spring Boot que maneja el request GET que
 * devuelve el clima para un día dado
 * 
 * @author andres
 *
 */
@RestController
public class WeatherController {

	@Autowired
	WeatherForecastGenerator forecastGenerator;

	@GetMapping("/clima")
	public PronosticoDiario weatherConditionByDay(@RequestParam Integer dia) {
		return new PronosticoDiario(dia, forecastGenerator.getWeatherConditionForDay(dia).toString());
	}

}

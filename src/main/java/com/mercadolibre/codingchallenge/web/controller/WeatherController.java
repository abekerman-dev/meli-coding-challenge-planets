package com.mercadolibre.codingchallenge.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mercadolibre.codingchallenge.logic.WeatherForecastGenerator;

/**
 * Único Controller de la aplicación Spring Boot que maneja el request GET que devuelve el clima para un día dado
 * @author andres
 *
 */
@Controller
public class WeatherController {
	
	WeatherForecastGenerator wfg = WeatherForecastGenerator.getInstance();

	@RequestMapping("/clima")
	public @ResponseBody String weatherConditionByDay(@RequestParam String dia) {
		return wfg.getWeatherConditionForDay(Integer.parseInt(dia)).toString();
	}
	
	@RequestMapping("/summary")
	public @ResponseBody String summary() {
		return wfg.getForecastSummary().toString();
	}
}

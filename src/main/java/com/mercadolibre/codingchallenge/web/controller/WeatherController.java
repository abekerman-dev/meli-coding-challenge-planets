package com.mercadolibre.codingchallenge.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.codingchallenge.logic.WeatherForecastGenerator;

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
	public DiaClimaJSONResponse weatherConditionByDay(@RequestParam Integer dia) {
		return new DiaClimaJSONResponse(dia, forecastGenerator.getWeatherConditionForDay(dia).toString());
	}
	
	private class DiaClimaJSONResponse {
		private int dia;
		private String clima;

		public DiaClimaJSONResponse(int dia, String clima) {
			this.dia = dia;
			this.clima = clima;
		}

		public int getDia() {
			return dia;
		}

		public void setDia(int dia) {
			this.dia = dia;
		}

		public String getClima() {
			return clima;
		}

		public void setClima(String clima) {
			this.clima = clima;
		}
		
	}

}

package com.mercadolibre.codingchallenge.main.console;

import com.mercadolibre.codingchallenge.logic.WeatherForecastGenerator;

/**
 * Clase principal que ejecuta el cáculo del pronóstico del clima en modo
 * batch/standalone (para invocar desde un CLI)
 * 
 * @author andres
 *
 */
public class Main {

	public static void main(String[] args) {
		WeatherForecastGenerator forecastGenerator = WeatherForecastGenerator.getInstance();
		System.out.println(forecastGenerator.getForecastSummary());
	}

}

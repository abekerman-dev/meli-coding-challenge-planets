package com.mercadolibre.codingchallenge.main.console;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.mercadolibre.codingchallenge.logic.WeatherForecastGenerator;
import com.mercadolibre.codingchallenge.pojo.WeatherForecastSummary;
import com.mercadolibre.codingchallenge.util.StringUtil;

/**
 * Clase principal que ejecuta el cálculo del pronóstico del clima e imprime el
 * resumen en la consola. Se ejecuta en modo batch/standalone (para invocar
 * desde la línea de comandos o vía un script)
 * 
 * @author andres
 *
 */
@Component
public class ConsoleRunner {

	@Autowired
	private WeatherForecastGenerator forecastGenerator;

	private static Logger log = LoggerFactory.getLogger(ConsoleRunner.class);

	public static void main(String[] args) {
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				WeatherForecastGenerator.class, ConsoleRunner.class)) {
			ConsoleRunner m = context.getBean(ConsoleRunner.class);
			m.start();
		}
	}

	private void start() {
		WeatherForecastSummary forecastSummary = forecastGenerator.getForecastSummary();
		log.info(StringUtil.NEW_LINE);
		log.info(forecastSummary.toString());
		log.info(StringUtil.NEW_LINE);
	}

}

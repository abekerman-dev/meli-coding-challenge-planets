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
 * Clase principal que ejecuta el cáculo del pronóstico del clima en modo
 * batch/standalone (para invocar desde un CLI)
 * 
 * @author andres
 *
 */
@Component
public class Main {

	@Autowired
	private WeatherForecastGenerator forecastGenerator;
	
	private static Logger log = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				WeatherForecastGenerator.class, Main.class)) {
			Main m = context.getBean(Main.class);
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

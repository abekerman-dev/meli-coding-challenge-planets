package com.mercadolibre.codingchallenge.main.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.mercadolibre.codingchallenge.logic.WeatherForecastGenerator;
import com.mercadolibre.codingchallenge.pojo.WeatherForecastSummary;
import com.mercadolibre.codingchallenge.util.StringUtil;

/**
 * 
 * @author andres
 *
 */
@Component
public class WeatherForecastPersistenceJob {

	@Autowired
	private WeatherForecastGenerator forecastGenerator;
	
	private static Logger log = LoggerFactory.getLogger(WeatherForecastPersistenceJob.class);

	public static void main(String[] args) {
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				WeatherForecastGenerator.class, WeatherForecastPersistenceJob.class)) {
			WeatherForecastPersistenceJob m = context.getBean(WeatherForecastPersistenceJob.class);
			m.start();
		}
	}

	private void start() {
	}

}

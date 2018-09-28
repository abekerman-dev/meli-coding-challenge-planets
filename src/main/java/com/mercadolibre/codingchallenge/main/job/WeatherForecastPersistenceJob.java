package com.mercadolibre.codingchallenge.main.job;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.mercadolibre.codingchallenge.logic.WeatherForecastGenerator;
import com.mercadolibre.codingchallenge.pojo.PronosticoDiario;
import com.mercadolibre.codingchallenge.repository.PronosticoDiarioRepository;
import com.mercadolibre.codingchallenge.util.DateUtil;

@Component
@EntityScan(basePackages = { "com.mercadolibre.codingchallenge" })
public class WeatherForecastPersistenceJob implements CommandLineRunner {

	@Autowired
	private PronosticoDiarioRepository repository;

	@Autowired
	private WeatherForecastGenerator forecastGenerator;

	public static void main(String[] args) throws Exception {
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				"com.mercadolibre.codingchallenge")) {
			WeatherForecastPersistenceJob m = context.getBean(WeatherForecastPersistenceJob.class);
			m.run(args);
		}
	}

	@Override
	public void run(String... args) throws Exception {
		IntStream.rangeClosed(1, (int) DateUtil.tenYearsAsDays()).boxed().forEach(
				day -> repository.save(new PronosticoDiario(day, forecastGenerator.getWeatherConditionForDay(day).toString())));
		
		List<PronosticoDiario> findAll = repository.findAll();
		findAll
			.forEach(System.out::println);
	}

}

package com.mercadolibre.codingchallenge.junit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mercadolibre.codingchallenge.enums.WeatherCondition;
import com.mercadolibre.codingchallenge.logic.WeatherForecastGenerator;

/**
 * Test unitario que verifica que el cálculo del pronóstico del tiempo es
 * correcto para una serie de días en particular
 * 
 * @author andres
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { WeatherForecastGenerator.class })
public class WeatherForecastGeneratorTestCase {

	@Autowired
	private WeatherForecastGenerator forecastGenerator;


	@Test
	public void day1ShouldBeUNDETERMINED() {
		assertEquals(WeatherCondition.UNDETERMINED, forecastGenerator.getWeatherConditionForDay(1));
	}

	@Test
	public void day72ShouldBeRAIN() {
		assertEquals(WeatherCondition.RAIN, forecastGenerator.getWeatherConditionForDay(72));
	}

	@Test
	public void day90ShouldBeDROUGHT() {
		assertEquals(WeatherCondition.DROUGHT, forecastGenerator.getWeatherConditionForDay(90));
	}

	@Test
	public void day180ShouldBeDROUGHT() {
		assertEquals(WeatherCondition.DROUGHT, forecastGenerator.getWeatherConditionForDay(180));
	}

	@Test
	public void day270ShouldBeDROUGHT() {
		assertEquals(WeatherCondition.DROUGHT, forecastGenerator.getWeatherConditionForDay(270));
	}

	@Test
	public void day360ShouldBeDROUGHT() {
		assertEquals(WeatherCondition.DROUGHT, forecastGenerator.getWeatherConditionForDay(360));
	}

}

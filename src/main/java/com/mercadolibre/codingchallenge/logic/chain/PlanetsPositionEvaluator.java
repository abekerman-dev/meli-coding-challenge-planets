package com.mercadolibre.codingchallenge.logic.chain;

import java.util.function.Predicate;

import com.mercadolibre.codingchallenge.enums.WeatherCondition;

/**
 * Patrón de diseño "chain of responsibility" que resuelve la evaluación en
 * cadena de ciertas condiciones que cada subclase determina a fin de determinar
 * la condición climática para un día dado. Implementa la interfaz Predicate ya
 * que se comporta como tal
 * 
 * @author andres
 *
 */
public abstract class PlanetsPositionEvaluator implements Predicate<Integer> {

	protected PlanetsPositionEvaluator nextEvaluator;
	protected WeatherCondition resultWeatherCondition;

	public PlanetsPositionEvaluator(PlanetsPositionEvaluator nextEvaluator, WeatherCondition resultWeatherCondition) {
		this.nextEvaluator = nextEvaluator;
		this.resultWeatherCondition = resultWeatherCondition;
	}

	public WeatherCondition getWeatherConditionForDay(int day) {
		if (test(day)) {
			return resultWeatherCondition;
		} else {
			return nextEvaluator == null ? WeatherCondition.UNDETERMINED : nextEvaluator.getWeatherConditionForDay(day);
		}
	}

}

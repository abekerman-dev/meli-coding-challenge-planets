package com.mercadolibre.codingchallenge.logic.chain;

import com.mercadolibre.codingchallenge.enums.WeatherCondition;

/**
 * Patrón de diseño "chain of responsibility" que resuelve la evaluación en
 * cadena de ciertas condiciones que cada subclase determina a fin de determinar
 * la condición climática para un día dado
 * 
 * @author andres
 *
 */
public abstract class PlanetsPositionEvaluator {

	protected PlanetsPositionEvaluator nextEvaluator;
	protected WeatherCondition resultWeatherCondition;

	public PlanetsPositionEvaluator(PlanetsPositionEvaluator nextEvaluator, WeatherCondition resultWeatherCondition) {
		this.nextEvaluator = nextEvaluator;
		this.resultWeatherCondition = resultWeatherCondition;
	}

	public WeatherCondition getWeatherConditionForDay(int day) {
		if (evaluate(day)) {
			return resultWeatherCondition;
		} else {
			return nextEvaluator == null ? WeatherCondition.UNDETERMINED : nextEvaluator.getWeatherConditionForDay(day);
		}
	}

	public abstract boolean evaluate(int day);

}

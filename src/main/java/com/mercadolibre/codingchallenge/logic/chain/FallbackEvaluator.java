package com.mercadolibre.codingchallenge.logic.chain;

import com.mercadolibre.codingchallenge.enums.WeatherCondition;

/**
 * Este evaluator es el último de la cadena y devuelve su clima asociado,
 * "indeterminado", directamente, ya que todas las evaluaciones anteriores
 * fallaron
 * 
 * @author andres
 *
 */
public class FallbackEvaluator extends PlanetsPositionEvaluator {

	public FallbackEvaluator() {
		super(null, WeatherCondition.UNDETERMINED);
	}

	@Override
	public boolean test(Integer t) {
		return true;
	}

}

package com.mercadolibre.codingchallenge.logic.chain;

import com.mercadolibre.codingchallenge.enums.WeatherCondition;
import com.mercadolibre.codingchallenge.util.AstronomyUtil;

public class PlanetsInTriangleWithSunInsideEvaluator extends PlanetsPositionEvaluator {

	public PlanetsInTriangleWithSunInsideEvaluator(PlanetsPositionEvaluator nextEvaluator) {
		super(nextEvaluator, WeatherCondition.RAIN);
	}

	@Override
	public boolean evaluate(int day) {
		return AstronomyUtil.arePlanetsInTriangleWithSunInside(day);
	}

}

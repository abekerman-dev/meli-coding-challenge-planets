package com.mercadolibre.codingchallenge.logic.chain;

import com.mercadolibre.codingchallenge.enums.WeatherCondition;
import com.mercadolibre.codingchallenge.util.AstronomyUtil;

public class PlanetsButNotSunAlignedEvaluator extends PlanetsPositionEvaluator {

	public PlanetsButNotSunAlignedEvaluator(PlanetsPositionEvaluator nextProcessor) {
		super(nextProcessor, WeatherCondition.OPTIMAL);
	}

	@Override
	public boolean evaluate(int day) {
		return AstronomyUtil.arePlanetsButNotSunAligned(day);
	}

}

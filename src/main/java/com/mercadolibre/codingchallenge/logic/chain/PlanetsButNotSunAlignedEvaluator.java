package com.mercadolibre.codingchallenge.logic.chain;

import com.mercadolibre.codingchallenge.enums.WeatherCondition;
import com.mercadolibre.codingchallenge.util.AstronomyUtil;

public class PlanetsButNotSunAlignedEvaluator extends PlanetsPositionEvaluator {

	public PlanetsButNotSunAlignedEvaluator(PlanetsPositionEvaluator nextProcessor) {
		super(nextProcessor, WeatherCondition.OPTIMAL);
	}

	@Override
	public boolean test(Integer day) {
		return AstronomyUtil.arePlanetsButNotSunAligned(day);
	}

}

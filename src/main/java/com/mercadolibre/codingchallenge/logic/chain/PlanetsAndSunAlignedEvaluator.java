package com.mercadolibre.codingchallenge.logic.chain;

import com.mercadolibre.codingchallenge.enums.WeatherCondition;
import com.mercadolibre.codingchallenge.util.AstronomyUtil;

public class PlanetsAndSunAlignedEvaluator extends PlanetsPositionEvaluator {

	public PlanetsAndSunAlignedEvaluator(PlanetsPositionEvaluator nextProcessor) {
		super(nextProcessor, WeatherCondition.DROUGHT);
	}

	@Override
	public boolean test(Integer day) {
		return AstronomyUtil.arePlanetsAndSunAligned(day);
	}

}

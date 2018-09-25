package com.mercadolibre.codingchallenge.logic.chain;

import com.mercadolibre.codingchallenge.enums.WeatherCondition;
import com.mercadolibre.codingchallenge.util.AstronomyUtil;

public class PlanetsAndSunAlignedEvaluator extends PlanetsPositionEvaluator {

	public PlanetsAndSunAlignedEvaluator(PlanetsPositionEvaluator nextProcessor) {
		super(nextProcessor, WeatherCondition.DROUGHT);
	}

	@Override
	public boolean evaluate(int day) {
		return AstronomyUtil.arePlanetsAndSunAligned(day);
	}

}

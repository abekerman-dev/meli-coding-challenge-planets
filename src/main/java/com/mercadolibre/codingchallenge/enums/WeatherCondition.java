package com.mercadolibre.codingchallenge.enums;

public enum WeatherCondition {
	DROUGHT("Sequía"), RAIN("Lluvia"), OPTIMAL("Condiciones óptimas de presión y temperatura"), UNDETERMINED("Clima Indeterminado");
	
	private String legend;
	
	private WeatherCondition(String legend) {
		this.legend = legend;
	}

	@Override
	public String toString() {
		return legend;
	}
}

package com.mercadolibre.codingchallenge.enums;

public enum RotationType {

	ROTATION_CLOCKWISE(-1), ROTATION_COUNTERCLOCKWISE(1);

	private final int sign;

	private RotationType(int sign) {
		this.sign = sign;
	}

	public int sign() {
		return sign;
	}
}

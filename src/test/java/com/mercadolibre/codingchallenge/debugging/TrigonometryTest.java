package com.mercadolibre.codingchallenge.debugging;

import org.junit.Test;

public class TrigonometryTest {
	
	final static double[] radians = { 0, Math.PI / 2, Math.PI, 3 * Math.PI / 2, 2 * Math.PI };
	final static double[] degrees = { 0, 45, 90, 135, 180, -45, -90, 360 };
	
//	@Test
//	public void testRadians() {
//		printSinCosTan(radians, false);
//	}
	
	@Test
	public void testDegrees() {
		printSinCosTan(degrees, true);
	}
	
//	@Test
	public void testDeg2Rad() {
		for (int deg = 0; deg <= 360; deg++) {
			double angdeg = deg; //degrees[deg];
			System.out.println(angdeg + " deg -> " + Math.toRadians(angdeg) / Math.PI + " rad");
		}
	}
	
	private void printSinCosTan(double[] value, boolean convertToRadians) {
		String degsOrRads = convertToRadians ? "Degrees" : "Rads";
		System.out.println(degsOrRads);
		for (int x = 0; x < value.length; x++) {
			double rads = value[x];
			double sin = Math.sin(convertToRadians ? Math.toRadians(rads) : rads);
			double cos = Math.cos(convertToRadians ? Math.toRadians(rads) : rads);
			double tan = Math.tan(convertToRadians ? Math.toRadians(rads) : rads);
			System.out.format("For " + degsOrRads + "=%f -> sin(x)=%f, cos(x)=%f, tan(x)=%f\n", (float) rads, sin, cos, tan);
		}
	}

}

package com.mercadolibre.codingchallenge.util;

import java.awt.geom.Point2D;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.mercadolibre.codingchallenge.pojo.Triangle;

public class MathUtil {

	public static double getUnsignedTangentByAngle(int angleDeg) {
		return truncateTo2DecimalPlaces(Math.abs(Math.tan(Math.toRadians(angleDeg))));
	}

	/**
	 * @param tangents la lista de tangentes
	 * @return true si todas las tangentes en la lista son 0
	 */
	public static boolean tangentsAreEqual(List<Double> tangents) {
		return 
			tangents
			.stream()
			.allMatch(tangents.get(0)::equals);
	}

	/**
	 * 
	 * @param p1
	 * @param p2
	 * @param p3
	 * @return true si los puntos pasados como parámetro tienen la misma pendiente
	 */
	public static boolean arePointsAlignedBySlope(Point2D p1, Point2D p2, Point2D p3) {
		double slopeP1P2 = (p2.getY() - p1.getY()) / (p2.getX() - p1.getX());
		double slopeP2P3 = (p3.getY() - p2.getY()) / (p3.getX() - p2.getX());

		return slopeP1P2 == slopeP2P3;
	}

	/**
	 * @param distanceToSun
	 * @param angleDeg
	 * @return La posición (x, y) (con 2 dígitos decimales) según la distancia al sol y el ángulo en grados
	 */
	public static Point2D getPositionByAngle(int distanceToSun, int angleDeg) {
		double angleRad = Math.toRadians(angleDeg);
		double x = distanceToSun * Math.cos(angleRad);
		double y = distanceToSun * Math.sin(angleRad);

		return new Point2D.Double(truncateTo2DecimalPlaces(x), truncateTo2DecimalPlaces(y));
	}

	/**
	 * @param triangleVertices
	 * @return una instancia de Triangle con los vértices dados
	 */
	public static Triangle createTriangle(List<Point2D> triangleVertices) {
		return new Triangle(triangleVertices.get(0), triangleVertices.get(1), triangleVertices.get(2));
	}

	/**
	 * @param planetPositions
	 * @return true si todas las posiciones de la lista están sobre el eje Y
	 */
	public static boolean areAllPointsOnYAxis(List<Point2D> planetPositions) {
		return
			planetPositions
			.stream()
			.map(Point2D::getX)
			.allMatch(p -> p.intValue() == 0);
	}
	
	/**
	 * 
	 * @param input
	 * @return el input pasado como parámetro truncado a 2 dígitos decimales
	 */
	private static double truncateTo2DecimalPlaces(double input) {
		return BigDecimal.valueOf(input).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}

}

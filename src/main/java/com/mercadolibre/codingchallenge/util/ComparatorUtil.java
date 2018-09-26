package com.mercadolibre.codingchallenge.util;

import java.awt.geom.Point2D;
import java.util.Comparator;

/**
 * Compara dos puntos en el plano, primero por su X y, si es igual, por su Y
 */
public class ComparatorUtil {

	private ComparatorUtil() {
	}

	public static Comparator<Point2D> point2DComparator() {
		return new Comparator<Point2D>() {

			@Override
			public int compare(Point2D p1, Point2D p2) {
				int compareX = Double.compare(p1.getX(), p2.getX());
				return compareX == 0 ? Double.compare(p1.getY(), p2.getY()) : compareX;
			}
		};
	}

}

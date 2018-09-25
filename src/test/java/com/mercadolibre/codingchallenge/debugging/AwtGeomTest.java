package com.mercadolibre.codingchallenge.debugging;

import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Point2D;

import org.junit.Test;

public class AwtGeomTest {

	@Test
	public void test() {
		int[] xpoints1 = {-2, 0, 2};
		int[] ypoints1 = {-1, 1, -2};
		Shape sampleTriangleWithSunInside = new Polygon(xpoints1, ypoints1, 3);
		System.out.println(sampleTriangleWithSunInside.contains(new Point2D.Double(0, 0)));
		
		int[] xpoints2 = {-2, -1, 0};
		int[] ypoints2 = {-1, 2, 1};
		Shape sampleTriangleWithSunOutside = new Polygon(xpoints2, ypoints2, 3);
		System.out.println(sampleTriangleWithSunOutside.contains(new Point2D.Double(0, 0)));
		
	}

}

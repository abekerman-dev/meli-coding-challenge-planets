package com.mercadolibre.codingchallenge.pojo;

import java.awt.Polygon;
import java.awt.geom.Point2D;

public class Triangle extends Polygon {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Point2D p1;
	private Point2D p2;
	private Point2D p3;

	public Triangle(Point2D p1, Point2D p2, Point2D p3) {
		super(new int[] { (int) p1.getX(), (int) p2.getX(), (int) p3.getX() },
				new int[] { (int) p1.getY(), (int) p2.getY(), (int) p3.getY() }, 3);
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
	}
	
	public double getPerimeter() {
		return p1.distance(p2) + p2.distance(p3) + p3.distance(p1);
	}

}

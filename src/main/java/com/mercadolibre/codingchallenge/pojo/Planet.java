package com.mercadolibre.codingchallenge.pojo;

import java.awt.geom.Point2D;

import com.mercadolibre.codingchallenge.enums.RotationType;
import com.mercadolibre.codingchallenge.util.MathUtil;

public class Planet {

	protected String name;
	protected int angularSpeed;
	protected RotationType rotationType;
	protected int distanceToSun;

	private Planet() {
	}
	
	private Planet(String name, int angularSpeed, RotationType rotationType, int distanceToSun) {
		this.name = name;
		this.distanceToSun = distanceToSun;
		this.angularSpeed = angularSpeed;
		this.rotationType = rotationType;
	}
	
	public static Planet getInstance(String name, int angularSpeed, RotationType rotationType, int distanceToSun) {
		return new Planet(name, angularSpeed, rotationType, distanceToSun);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAngularSpeed() {
		return angularSpeed;
	}

	public void setAngularSpeed(int angularSpeed) {
		this.angularSpeed = angularSpeed;
	}

	public RotationType getRotationType() {
		return rotationType;
	}

	public void setRotationType(RotationType rotationType) {
		this.rotationType = rotationType;
	}

	public int getDistanceToSun() {
		return distanceToSun;
	}

	public void setDistanceToSun(int distanceToSun) {
		this.distanceToSun = distanceToSun;
	}

	public int getRotationAngleByDay(int day) {
		return (rotationType.sign() * angularSpeed * day) % 360;
	}
	
	public double getUnsignedTangentByDay(int day) {
		return MathUtil.getUnsignedTangentByAngle(this.getRotationAngleByDay(day));
	}

	public Point2D getPositionByDay(int day) {
		return MathUtil.getPositionByAngle(distanceToSun, getRotationAngleByDay(day));
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getClass().getSimpleName() + " Planet {");
		sb.append("name: " + name);
		sb.append(", angularSpeed: " + angularSpeed);
		sb.append(", rotationType: " + rotationType);
		sb.append(", distanceToSun: " + distanceToSun);
		sb.append("}");
		return sb.toString();
	}

}

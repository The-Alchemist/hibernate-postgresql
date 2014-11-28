package com.github.thealchemist.pg_hibernate.types;

import java.awt.geom.Ellipse2D;
import java.io.Serializable;

/**
 * A simple immutable circle object.
 *
 * @author Jesse Costello-Good
 * @version $Id$
 */
public class Circle implements Serializable, Cloneable {

	private final Point center;
	private final double radius;

	public Circle( Point center, double radius ) {
		this.center = center;
		this.radius = radius;
	}

	public Point getCenter() {
		return center;
	}

	public double getRadius() {
		return radius;
	}

	public Ellipse2D asEllipse2D() {
		return new Ellipse2D.Double(center.getX() - radius, center.getY() - radius, radius * 2, radius * 2);
	}

	public int hashCode() {
		return (int) radius * 31 + center.hashCode();
	}

	public boolean equals( Object obj ) {
		if (obj instanceof Circle) {
			return radius == ((Circle) obj).radius && center.equals(((Circle) obj).getCenter());
		}
		return super.equals(obj);
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}

package com.github.thealchemist.pg_hibernate.types;

import java.awt.geom.Line2D;
import java.io.Serializable;

/**
 * A simple immutable (directed) line segment object.
 *
 * @author Jesse Costello-Good
 * @version $Id$
 */
public class LineSegment implements Serializable, Cloneable {

	private final Point p1;
	private final Point p2;

	public LineSegment( Point p1, Point p2 ) {
		this.p1 = p1;
		this.p2 = p2;
	}

	public Point getP1() {
		return p1;
	}

	public Point getP2() {
		return p2;
	}

	public int hashCode() {
		return p1.hashCode() * 29 + p2.hashCode();
	}

	public boolean equals( Object obj ) {
		if (obj instanceof LineSegment) {
			return p1.equals(((LineSegment) obj).p1) && p2.equals(((LineSegment) obj).p2);
		}
		return super.equals(obj);
	}

	public Line2D asLine2D() {
		return new Line2D.Double(p1.asPoint2D(), p2.asPoint2D());
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}

package com.github.thealchemist.pg_hibernate.types;

import java.awt.geom.Rectangle2D;
import java.io.Serializable;

/**
 * A simple immutable rectangle object.
 *
 * @author Jesse Costello-Good
 * @version $Id$
 */
public class Box implements Serializable, Cloneable {

	private final Point p1;
	private final Point p2;

	public Box( Point p1, Point p2 ) {
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
		return p1.hashCode() + p2.hashCode();
	}

	public boolean equals( Object obj ) {
		if (obj instanceof Box) {
			return (p1.equals(((Box) obj).p1) && p2.equals(((Box) obj).p2)) ||
					(p1.equals(((Box) obj).p2) && p2.equals(((Box) obj).p1));
		}
		return super.equals(obj);
	}

	public Rectangle2D asRectangle2D() {
		return new Rectangle2D.Double(p1.getX(), p1.getY(), p2.getX() - p1.getX(), p2.getY() - p1.getY());
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}

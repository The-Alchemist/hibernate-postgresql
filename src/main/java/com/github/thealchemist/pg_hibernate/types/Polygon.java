package com.github.thealchemist.pg_hibernate.types;

import java.io.Serializable;
import java.util.Arrays;

/**
 * A simple immutable polygon object.
 *
 * @author Jesse Costello-Good
 * @version $Id$
 */
public class Polygon implements Serializable, Cloneable {

	private final Point[] points;

	public Polygon( Point[] points ) {
		this.points = points;
	}

	public Point[] getPoints() {
		return (Point[]) points.clone();
	}

	public int hashCode() {
		return points.hashCode();
	}

	public boolean equals( Object obj ) {
		if (obj instanceof Polygon) {
			return Arrays.equals(points, ((Polygon) obj).points);
		}
		return super.equals(obj);
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	/*
	public GeneralPath asGeneralPath() {
		GeneralPath path = new GeneralPath();
		path.append(new PolygonPathIterator(points), false);
		return path;
	}

	private static class PolygonPathIterator implements PathIterator {

		private final Point[] points;
		private int index = 0;

		public PolygonPathIterator( Point[] points ) {
			this.points = points;
		}

		public int getWindingRule() {
			return 0;
		}

		public boolean isDone() {
			return index > points.length;
		}

		public void next() {
			index++;
		}

		public int currentSegment( float[] coords ) {
			if (index < points.length) {
				coords[0] = (float) points[index-1].getX();
				coords[1] = (float) points[index-1].getY();
			} else {
				coords[0] = (float) points[0].getX();
				coords[1] = (float) points[0].getY();
			}
			return segmentType();
		}

		public int currentSegment( double[] coords ) {
			if (index < points.length) {
				coords[0] = points[index-1].getX();
				coords[1] = points[index-1].getY();
			} else {
				coords[0] = points[0].getX();
				coords[1] = points[0].getY();
			}
			return segmentType();
		}

		private int segmentType() {
			if (index == 1) {
				return SEG_MOVETO;
			} else if (index == points.length) {
				return SEG_CLOSE;
			} else {
				return SEG_LINETO;
			}
		}
	}
	*/
}

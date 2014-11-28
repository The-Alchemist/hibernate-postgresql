package com.github.thealchemist.pg_hibernate.spring;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.github.thealchemist.pg_hibernate.types.Point;

@Entity
public class PointTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Point point;

	public Integer getId() {
		return id;
	}

	public void setId( Integer id ) {
		this.id = id;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint( Point point ) {
		this.point = point;
	}
}
package com.github.thealchemist.pg_hibernate.spring;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.github.thealchemist.pg_hibernate.types.Polygon;

@Entity
public class PolygonTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Polygon polygon;

	public Integer getId() {
		return id;
	}

	public void setId( Integer id ) {
		this.id = id;
	}

	public Polygon getPolygon() {
		return polygon;
	}

	public void setPolygon( Polygon polygon ) {
		this.polygon = polygon;
	}
}
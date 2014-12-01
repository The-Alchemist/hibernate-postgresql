package com.github.thealchemist.pg_hibernate.spring;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Type;

import com.github.thealchemist.pg_hibernate.types.Polygon;

@Entity
public class PolygonTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
    @Column(name="my_polygon")
    @Type(type="polygon")
	private Polygon myPolygon;

	public Integer getId() {
		return id;
	}

	public void setId( Integer id ) {
		this.id = id;
	}

	public Polygon getPolygon() {
		return myPolygon;
	}

	public void setPolygon( Polygon polygon ) {
		this.myPolygon = polygon;
	}
}
package com.github.thealchemist.pg_hibernate.spring;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Type;

import com.github.thealchemist.pg_hibernate.types.Circle;

@Entity
public class CircleTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
    @Type(type="circle")
	private Circle circle;

	public Integer getId() {
		return id;
	}

	public void setId( Integer id ) {
		this.id = id;
	}

	public Circle getCircle() {
		return circle;
	}

	public void setCircle( Circle circle ) {
		this.circle = circle;
	}
}
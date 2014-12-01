package com.github.thealchemist.pg_hibernate.spring;

import javax.persistence.Column;
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
    @Column(name="my_circle")
    @Type(type="circle")
	private Circle myCircle;

	public Integer getId() {
		return id;
	}

	public void setId( Integer id ) {
		this.id = id;
	}

	public Circle getCircle() {
		return myCircle;
	}

	public void setCircle( Circle circle ) {
		this.myCircle = circle;
	}
}
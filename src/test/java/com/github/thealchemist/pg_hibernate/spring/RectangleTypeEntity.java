package com.github.thealchemist.pg_hibernate.spring;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.github.thealchemist.pg_hibernate.types.Rectangle;

@Entity
public class RectangleTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Rectangle rect;

	public Integer getId() {
		return id;
	}

	public void setId( Integer id ) {
		this.id = id;
	}

	public Rectangle getRect() {
		return rect;
	}

	public void setRect( Rectangle rect ) {
		this.rect = rect;
	}
}
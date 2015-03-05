package com.github.thealchemist.pg_hibernate.spring;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Type;

@Entity
public class IntegerArrayEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
    @Type(type="intarray")
	private Integer[] integers;

	public Integer getId() {
		return id;
	}

	public void setId( Integer id ) {
		this.id = id;
	}

	public Integer[] getIntegers() {
		return integers;
	}

	public void setIntegers( Integer[] strings ) {
		this.integers = strings;
	}
}
package com.github.thealchemist.pg_hibernate.spring;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Type;

import com.github.thealchemist.pg_hibernate.types.Box;

@Entity
public class BoxTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
    @Column(name="my_box")
    @Type(type="box")
	private Box myBox;

	public Integer getId() {
		return id;
	}

	public void setId( Integer id ) {
		this.id = id;
	}

	public Box getBox() {
		return myBox;
	}

	public void setBox( Box box ) {
		this.myBox = box;
	}
}
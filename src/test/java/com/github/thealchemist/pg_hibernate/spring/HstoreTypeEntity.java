package com.github.thealchemist.pg_hibernate.spring;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Type;

@Entity
public class HstoreTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
    @Type(type="hstore")
	private Map<String, String> map;

	public Integer getId() {
		return id;
	}

	public void setId( Integer id ) {
		this.id = id;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap( Map<String, String> map ) {
		this.map = map;
	}
}
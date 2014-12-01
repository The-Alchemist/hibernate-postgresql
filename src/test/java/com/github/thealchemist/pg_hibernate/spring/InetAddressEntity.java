package com.github.thealchemist.pg_hibernate.spring;

import java.net.InetAddress;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Type;

@Entity
public class InetAddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
    @Type(type="inet")
	private InetAddress address;

	public Integer getId() {
		return id;
	}

	public void setId( Integer id ) {
		this.id = id;
	}

	public InetAddress getAddress() {
		return address;
	}

	public void setAddress( InetAddress address ) {
		this.address = address;
	}
}
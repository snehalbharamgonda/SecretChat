package com.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Message {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getActual() {
		return actual;
	}


	public void setActual(String actual) {
		this.actual = actual;
	}


	public String getSecret() {
		return secret;
	}


	public void setSecret(String secret) {
		this.secret = secret;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	@Column(length = 9999)
	private String actual;

	@Column(length = 30)
	private String secret;
	
	
	private String password;
	
}

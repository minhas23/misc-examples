package com.manjeet.sample.java.hibernate;

import java.io.Serializable;

public class DbObject implements Serializable {
	

	private static final long serialVersionUID = 8739969040170281659L;
	String name;
	Integer age;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}

}

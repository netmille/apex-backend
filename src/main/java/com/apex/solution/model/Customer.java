package com.apex.solution.model;

public class Customer {

	private int id;
	private String lastName;
	private String firstName;
	private String country;
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@Override
	public String toString() {
		return "Customer [id=" + this.getId() + ", lastName=" + this.getLastName() + ", firstName=" + this.getFirstName() + ",country=" + this.getCountry() + "]";
	}
	
	
	
}

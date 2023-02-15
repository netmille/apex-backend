package com.apex.solution.dto;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author turik.campbell
 *
 */
public class CustomerPoint {

	private static String months[] = {"January", "February", "March", "April",
             "May", "June", "July", "August", "September",
             "October", "November", "December"};
	 
	private String lastName;
	private String firstName;
	private int points;
	
	private int year;
	private int month;
	
	private int customerId;
	
	@JsonProperty("customerId")
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	@JsonProperty("lastname")
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	
	@JsonProperty("firstname")
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@JsonProperty("points")
	public int getPoints() {
		return points;
	}
	
	
	public void setPoints(int points) {
		this.points = points;
	}
	
	@JsonProperty("year")
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	@JsonIgnore
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	
	@JsonProperty("month")
	public String getMonthAsString()
	{
		return months[this.month];
	}
	
	@Override
	public String toString() {
		return "CustomerPoint [lastName=" + this.getLastName()+ ", firstName=" + this.getFirstName() + ", points=" + this.getPoints() + ", year="
				+ this.getYear() + ", month=[" + getMonth() + ","+ this.getMonthAsString() + "], customerId=" + this.getCustomerId() + "]";
	}
	
	
	
	
}

package com.apex.solution.model;

import java.util.Date;

/**
 * 
 * @author turik.campbell
 *
 */
public class Purchase {

	private int orderId;
	private double total;
	private Customer customer;
	
	private Date orderDate;
	
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	
	}
	@Override
	public String toString() {
		return "Purchase [orderId=" + getOrderId() + ", total=" + getTotal()+ ", customer=" + this.getCustomer() + ", orderDate="
				+ this.getOrderDate() + "]";
	}
	
	
}

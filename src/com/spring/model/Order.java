package com.spring.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="orders")
public class Order {
	@Id
	private int id;
	@OneToMany
	@JoinColumn(name="order_id")
	private List <LineItem> lineItems;
	@ManyToOne
	private User user;
	
	public List<LineItem> getLineItems() {
		return lineItems;
	}
	public void setLineItems(List<LineItem> lineItems) {
		this.lineItems = lineItems;
	}
	public void addToLineItems(LineItem lineItem){
		if(this.lineItems==null){
			this.lineItems = new ArrayList<LineItem>();
			this.lineItems.add(lineItem);
		}
		else{
			this.lineItems.add(lineItem);
		}
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

}

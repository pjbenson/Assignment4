package com.spring.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.IndexColumn;

@Entity
@Table(name="orders")
public class Order {
	@Id
	private int id;
	@OneToMany(mappedBy = "order", fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<LineItem> lineitems;
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<LineItem> getLineitems() {
		return lineitems;
	}
	public void setLineitems(List<LineItem> lineitems) {
		this.lineitems = lineitems;
	}
}

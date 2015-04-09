package com.spring.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.IndexColumn;

@Entity
@Table(name="cart")
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id", nullable = false)
	private int id;
	@OneToMany(cascade = {CascadeType.ALL})
	@JoinColumn(name="cart_id")
	private List<LineItem> lineitems;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<LineItem> getLineitems() {
		return lineitems;
	}
	public void addLineitem(LineItem lineitem) {
		if(lineitems == null){
			lineitems = new ArrayList<LineItem>();
			this.lineitems.add(lineitem);
		}else{
			this.lineitems.add(lineitem);
		}
		
	}
	
}

package com.spring.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="cart")
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@OneToMany
	@JoinColumn(name="stock_id")
	private List<Stock> stock;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Stock> getStock() {
		return stock;
	}
	public void setStock(List<Stock> stock) {
		this.stock = stock;
	}

}

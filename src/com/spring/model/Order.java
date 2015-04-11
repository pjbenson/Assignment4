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
	@OneToMany(fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(name = "order_stock", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "stock_id"))
	private List<Stock> stock;
	
	public List<Stock> getStock() {
		return stock;
	}
	public void setStock(List<Stock> stock) {
		this.stock = stock;
	}
	public void addToStock(Stock stock){
		if(this.stock==null){
			this.stock = new ArrayList<Stock>();
			this.stock.add(stock);
		}
		else{
			this.stock.add(stock);
		}
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}

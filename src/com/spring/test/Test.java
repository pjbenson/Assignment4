package com.spring.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.spring.model.Category;
import com.spring.model.Stock;

public class Test {
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public Test(){
		emf = Persistence.createEntityManagerFactory("Assignment4");
		em = emf.createEntityManager();
		
		Stock stock = new Stock();
		Category cat = new Category();
		cat.setCategoryTitle("Hardware");
		stock.setTitle("Drill");
		stock.setManufacturer("Black&Decker");
		stock.setPrice(100.0);
		stock.setCategory(cat);
		cat.addToStock(stock);
		
		em.getTransaction().begin();
		em.persist(stock);
		em.persist(cat);
		em.getTransaction().commit();
		em.close();
	}

	public static void main(String[] args) {
		new Test();
	}

}

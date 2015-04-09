package com.spring.service;

import java.util.List;

import com.spring.model.Category;
import com.spring.model.Stock;

public interface StockService {

	public List<Stock> getAllStock();

	public List<Category> getCategories();

	public void saveStock(Stock stock);

	public void saveCategory(Category cat);
	
	public Stock getStockById(int id);

}

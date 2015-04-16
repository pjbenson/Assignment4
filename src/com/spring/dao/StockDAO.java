package com.spring.dao;

import java.util.List;

import com.spring.model.Category;
import com.spring.model.Review;
import com.spring.model.Stock;

public interface StockDAO {
	
	public void saveStock(Stock stock);
	
	public void saveCategory(Category cat);
	
	public List<Stock> getAllStock();
	
	public Stock getStockById(int id);
	
	public void updateStock(Stock stock);
	
	public List<Category> getCategories();
	
	public void saveReview(Review review);

}

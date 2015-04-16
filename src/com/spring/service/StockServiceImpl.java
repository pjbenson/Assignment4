package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dao.StockDAO;
import com.spring.model.Category;
import com.spring.model.Review;
import com.spring.model.Stock;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class StockServiceImpl implements StockService {
	
	@Autowired
	private StockDAO stockDAO;

	@Override
	public List<Stock> getAllStock() {
		return stockDAO.getAllStock();
	}

	@Override
	public List<Category> getCategories() {
		return stockDAO.getCategories();
	}

	@Override
	public void saveStock(Stock stock) {
		stockDAO.saveStock(stock);
		
	}

	@Override
	public void saveCategory(Category cat) {
		stockDAO.saveCategory(cat);
	}

	@Override
	public Stock getStockById(int id) {
		return stockDAO.getStockById(id);
	}

	@Override
	public void updateStock(Stock stock) {
		stockDAO.updateStock(stock);
	}

	@Override
	public void saveReview(Review review) {
		stockDAO.saveReview(review);
	}

}

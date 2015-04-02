package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.spring.dao.StockDAO;
import com.spring.model.Stock;

public class StockServiceImpl implements StockService {
	
	@Autowired
	private StockDAO stockDAO;

	@Override
	public List<Stock> getAllStock() {
		return stockDAO.getAllStock();
	}

}

package com.spring.dao;

import com.spring.model.LineItem;

public interface LineItemDAO {
	
	public void saveLineItem(LineItem lineItem);
	
	public void deleteLineItem(int id);
	
	public LineItem getItemById(int id);
	
	public void mergeLineItem(LineItem lineItem);
}

package com.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dao.LineItemDAO;
import com.spring.model.LineItem;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class LineItemServiceImpl implements LineItemService {
	
	@Autowired
	private LineItemDAO lineDAO;
	
	@Override
	public void saveLineItem(LineItem lineItem) {
		lineDAO.saveLineItem(lineItem);
	}

	@Override
	public void deleteLineItem(int id) {
		lineDAO.deleteLineItem(id);
	}

	@Override
	public LineItem getItemById(int id) {
		// TODO Auto-generated method stub
		return lineDAO.getItemById(id);
	}

	@Override
	public void mergeLineItem(LineItem lineItem) {
		lineDAO.mergeLineItem(lineItem);		
	}

}

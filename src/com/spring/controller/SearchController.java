package com.spring.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.spring.model.Stock;
import com.spring.service.StockService;
import com.spring.service.UserService;

@Controller
public class SearchController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private StockService stockService;
	
	@RequestMapping(value="/titleSearch", method = RequestMethod.POST)
	public @ResponseBody void getStock(@RequestParam String value, HttpServletResponse response) throws IOException{
		response.setContentType("application/json");
		String g = new Gson().toJson(getStock(value));
		response.getWriter().write(g);
	}
	
	private List<Stock> getStock(String title){
		List<Stock> stock = new ArrayList<Stock>();
		
		for(Stock s: stockService.getAllStock()){
			if(s.getCategory().equals(title)){
				stock.add(s);
			}
		}
		return stock;
	}

}

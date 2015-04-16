package com.spring.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;

import com.spring.model.Review;
import com.spring.model.Stock;
import com.spring.model.User;
import com.spring.service.StockService;

@Controller
@SessionAttributes({"stock", "stockReviews"})
public class StockController {
	@Autowired
	private StockService stockService;
	
	@RequestMapping(value = "/stock/{id}")
	public String showStockPage(@PathVariable("id") Integer id, ModelMap model){
		Stock stock = stockService.getStockById(id);
		model.put("stockReviews", stock.getReviews());
		model.addAttribute("review", new Review());
		model.put("stock", stock);
		return ("stock");
	}
	
	@RequestMapping(value = "/addReview", method = RequestMethod.POST)
	public ModelAndView addReview(@ModelAttribute("review") Review review, ModelMap model){
		User user = (User) RequestContextHolder.currentRequestAttributes().getAttribute("user", RequestAttributes.SCOPE_SESSION);
		Stock stock = (Stock) RequestContextHolder.currentRequestAttributes().getAttribute("stock", RequestAttributes.SCOPE_SESSION);
		review.setDate(new Date());
		review.setUser(user);
		stock.setRating((review.getRating()+stock.getRating())/stock.getReviews().size());
		review.setStock(stock);
		stockService.saveReview(review);
		return new ModelAndView("redirect:/stock/"+stock.getId()+".html");
	}
	
	private void updateStockRating(Stock stock, int rating){
		
	}
}
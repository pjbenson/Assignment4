package com.spring.controller;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

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

import com.spring.model.LineItem;
import com.spring.model.Order;
import com.spring.model.Stock;
import com.spring.model.User;
import com.spring.service.LineItemService;
import com.spring.service.StockService;
import com.spring.service.UserService;

@Controller
@SessionAttributes({"user", "cart", "cartContents", "cartSize", "cartTotal"})
public class CartController {

	@Autowired
	private StockService stockService;
	@Autowired
	private UserService userService;
	@Autowired
	private LineItemService lineItemService;
	private List<LineItem> cart;

	@RequestMapping("/addToCart/{id}")
	public ModelAndView addToCart(@PathVariable("id") Integer id, ModelMap model){
		if(RequestContextHolder.currentRequestAttributes().getAttribute("cart", RequestAttributes.SCOPE_SESSION) != null){
			Stock stock = stockService.getStockById(id);
			LineItem lineItem = new LineItem();
			lineItem.setStock(stock);
			lineItem.setLineTotal(stock.getPrice());
			lineItemService.saveLineItem(lineItem);
			cart.add(lineItem);
			model.put("cart", cart);
		}
		else{
			Stock stock = stockService.getStockById(id);
			LineItem lineItem = new LineItem();
			lineItem.setStock(stock);
			lineItem.setLineTotal(stock.getPrice());
			lineItemService.saveLineItem(lineItem);
			cart = new ArrayList<LineItem>();
			cart.add(lineItem);
			model.put("cart", cart);
		}

		model.put("cartSize", cart.size());
		model.put("cartContents", cart);
		model.put("cartTotal", getCartTotal());
		return new ModelAndView("redirect:/profile.html");
	}

	@RequestMapping(value = "/viewCart", method = RequestMethod.GET)
	public ModelAndView showCart(){
		return new ModelAndView("viewCart");
	}

	@RequestMapping(value = "/confirmOrder", method = RequestMethod.GET)
	public ModelAndView buyStock(ModelMap model){
		List<LineItem> sessionCart = (List<LineItem>) RequestContextHolder.currentRequestAttributes().getAttribute("cart", RequestAttributes.SCOPE_SESSION);
		User user = (User) RequestContextHolder.currentRequestAttributes().getAttribute("user", RequestAttributes.SCOPE_SESSION);
		User tempUser = userService.getUser(user.getId());

		Order order = new Order();
		List<Stock> stock = new ArrayList<Stock>();
		for(LineItem li: sessionCart){
			stock.add(li.getStock());
		}
		order.setStock(stock);
		tempUser.addToOrders(order);
		userService.updateUser(user);

		updateStockLevels(sessionCart);
		chargeUserCreditCard(PaymentStrategy, getCartTotal());
		sessionCart.clear();
		cart.clear();

		model.put("orders", order);
		model.put("cartSize", cart.size());
		model.put("cartTotal", getCartTotal());
		return new ModelAndView("redirect:/orders.html");
	}

	@RequestMapping("/removeStock/{id}")
	public ModelAndView removeStock(@PathVariable("id") Integer id, ModelMap model){
		cart.clear();
		//TODO remove individual lineitem for list
		model.put("cart", cart);
		model.put("cartSize", cart.size());
		model.put("cartTotal", getCartTotal());
		return new ModelAndView("redirect:/viewCart.html");
	}

	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public ModelAndView showOrders(ModelMap model){
		User user = (User) RequestContextHolder.currentRequestAttributes().getAttribute("user", RequestAttributes.SCOPE_SESSION);
		//model.addAttribute("creditCard", user.getAccount().getCreditCards().get(0).getCardType());

		return new ModelAndView("orders");
	}

	private Double getCartTotal(){
		Double total = 0.;
		for(LineItem li: cart){
			total += li.getStock().getPrice();
		}
		return total;
	}

	private void updateStockLevels(List<LineItem> lineItems){
		for(LineItem li: lineItems){
			Stock stock = stockService.getStockById(li.getStock().getId());
			int newQuantity = stock.getQuantity()-1;
			stock.setQuantity(newQuantity);
			stockService.updateStock(stock);
		}
	}

	private void chargeUserCreditCard(User user, Double cartTotal){
		user.getAccount().getCreditCards().get(0).pay(cartTotal);
	}
}

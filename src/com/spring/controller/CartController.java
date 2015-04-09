package com.spring.controller;


import java.util.ArrayList;
import java.util.List;

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

import com.spring.model.Cart;
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
	private List<LineItem> lineItems;
	private Cart cart;

	@RequestMapping("/addToCart/{id}")
	public ModelAndView addToCart(@PathVariable("id") Integer id, ModelMap model){
		Cart sessionCart = (Cart) RequestContextHolder.currentRequestAttributes().getAttribute("cart", RequestAttributes.SCOPE_SESSION);
		if(sessionCart != null){
			Stock stock = stockService.getStockById(id);
			LineItem lineItem = new LineItem();
			lineItem.setCart(cart);
			lineItem.setStock(stock);
			lineItem.setLineTotal(stock.getPrice());
			cart.addLineitem(lineItem);
			lineItemService.saveLineItem(lineItem);
			lineItems.add(lineItem);
		}
		else{
			cart = new Cart();
			Stock stock = stockService.getStockById(id);
			LineItem lineItem = new LineItem();
			lineItem.setCart(cart);
			lineItem.setStock(stock);
			lineItem.setLineTotal(stock.getPrice());
			cart.addLineitem(lineItem);
			lineItemService.saveLineItem(lineItem);
			lineItems = new ArrayList<LineItem>();
			lineItems.add(lineItem);
		}
		userService.updateCart(cart);
		model.put("cart", cart);
		model.put("cartSize", cart.getLineitems().size());
		model.put("cartContents", cart.getLineitems());
		model.put("cartTotal", getCartTotal());
		return new ModelAndView("redirect:/profile.html");
	}

	@RequestMapping(value = "/viewCart", method = RequestMethod.GET)
	public ModelAndView showCart(){
		return new ModelAndView("viewCart");
	}

	@RequestMapping(value = "/confirmOrder", method = RequestMethod.GET)
	public ModelAndView buyStock(ModelMap model){
		Cart cart = (Cart) RequestContextHolder.currentRequestAttributes().getAttribute("cart", RequestAttributes.SCOPE_SESSION);
		User user = (User) RequestContextHolder.currentRequestAttributes().getAttribute("user", RequestAttributes.SCOPE_SESSION);
		
		return new ModelAndView("redirect:/viewCart.html");
	}

	@RequestMapping("/removeStock/{id}")
	public ModelAndView removeStock(@PathVariable("id") Integer id, ModelMap model){
		lineItemService.deleteLineItem(id);
		cart.getLineitems().clear();
		//TODO remove individual lineitem for list
		model.put("cartSize", cart.getLineitems().size());
		return new ModelAndView("redirect:/viewCart.html");
	}
	
	private Double getCartTotal(){
		Double total = 0.;
		for(LineItem li: cart.getLineitems()){
			total += li.getStock().getPrice();
		}
		return total;
	}
	
	

}

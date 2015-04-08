package com.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.model.AddStockForm;
import com.spring.model.Category;
import com.spring.model.Stock;
import com.spring.model.User;
import com.spring.service.AccountService;
import com.spring.service.LoginService;
import com.spring.service.RoleService;
import com.spring.service.StockService;
import com.spring.service.UserService;

@Controller
public class AdminController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private LoginService loginService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private StockService stockService;
	@Autowired
	private AccountService accService;
	
	@RequestMapping(value = "/adminProfile", method = RequestMethod.GET)
	public ModelAndView showAdminProfile(ModelMap model) {
		List<User> users = userService.userList();
		List<Stock> stock = stockService.getAllStock();
		
		model.addAttribute("users", users);
		model.addAttribute("stock", stock);
		return new ModelAndView("adminProfile");
	}
	
	@RequestMapping(value = "/addStock", method = RequestMethod.GET)
	public ModelAndView showAddStockPage(ModelMap model){
		Stock stock = new Stock();
		List<String> categories = new ArrayList<String>();
		List<Category> cats = stockService.getCategories();
		for(Category c:cats){
			if(!categories.contains(c.getCategoryTitle())){
				categories.add(c.getCategoryTitle());
			}
		}
		
		model.addAttribute("categories", categories);
		model.addAttribute("stock", stock);
		return new ModelAndView("addStock");
	}
	
	@RequestMapping(value = "/addStock", method = RequestMethod.POST)
	public ModelAndView addStock(@ModelAttribute("stock")AddStockForm stock, ModelMap model){
		Stock s = new Stock();
		s.setTitle(stock.getTitle());
		s.setManufacturer(stock.getManufacturer());
		s.setPrice(stock.getPrice());
		s.setQuantity(stock.getQuantity());
		for(Category c: stockService.getCategories()){
			if(c.getCategoryTitle().equalsIgnoreCase(stock.getCategory())){
				s.setCategory(c);
			}
		}
		stockService.saveStock(s);
		return new ModelAndView("addStock");
	}
	
	@RequestMapping(value = "/addCategory", method = RequestMethod.GET)
	public ModelAndView showAddCategory(ModelMap model){
		Category cat = new Category();
		model.addAttribute("category", cat);
		return new ModelAndView("addCategory");
	}
	
	@RequestMapping(value = "/addCategory", method = RequestMethod.POST)
	public ModelAndView addCategory(@ModelAttribute("category") Category cat){
		stockService.saveCategory(cat);
		return new ModelAndView("addCategory");
	}
	
	@RequestMapping("/delete/{id}")
	public ModelAndView deleteUser(@PathVariable("id") Integer id) {
		userService.deleteUser(id);
		return new ModelAndView("redirect:/adminProfile.html");
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.GET)
	public ModelAndView editPage(ModelMap model) {
		User user = new User();
		model.addAttribute("user",user);
		return new ModelAndView("adminProfile");
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.POST)
	public ModelAndView editUser(@ModelAttribute User user){
		return null;
	}

}

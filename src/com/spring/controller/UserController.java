package com.spring.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;

import com.spring.bean.UserBean;
import com.spring.model.Account;
import com.spring.model.Order;
import com.spring.model.Role;
import com.spring.model.SearchStockForm;
import com.spring.model.Stock;
import com.spring.model.User;
import com.spring.service.AccountService;
import com.spring.service.LoginService;
import com.spring.service.OrderService;
import com.spring.service.RoleService;
import com.spring.service.StockService;
import com.spring.service.UserService;
import com.spring.strategy.CreditCard;

@Controller
@SessionAttributes({"user", "cart", "cartContents", "account"})
public class UserController {

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
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveUser(@ModelAttribute("command") UserBean userBean, BindingResult result, ModelMap model) {
		User user = prepareModel(userBean);
		User checkIfUserExists = loginService.checkLogin(user.getUserName(), user.getPassword());
		Role role_user = roleService.getRole(6);
		if(checkIfUserExists == null){
			Account acc = new Account();
			acc.setAddress("");
			user.setRole(role_user);
			user.setAccount(acc);
			userService.addUser(user);
			model.addAttribute("account", acc);
			return new ModelAndView("redirect:/loginform.html");
		}
		else{
			return new ModelAndView("redirect:/register.html");
		}
	}
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView showProfile(ModelMap model) {
		User user = (User) RequestContextHolder.currentRequestAttributes().getAttribute("user", RequestAttributes.SCOPE_SESSION);
		List<Stock> stock = stockService.getAllStock();
		
		model.addAttribute("stock", stock);
		model.addAttribute("ascOrDesc", new SearchStockForm());
		model.addAttribute("searchObject", new SearchStockForm());
		//model.addAttribute("creditCard", user.getAccount().getCreditCards().get(0).getCardType());
		return new ModelAndView("profile");
	}
	
	@RequestMapping(value="/users", method = RequestMethod.GET)
	public ModelAndView listUsers() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("users",  prepareListofBean(userService.userList()));
		return new ModelAndView("userList", model);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showUpdateBalance(ModelMap model) {
		Account acc = new Account();
		model.addAttribute("account", acc);
		return new ModelAndView("updateBalance");
	}
	
	@RequestMapping(value = "/addCard", method = RequestMethod.GET)
	public String showAddCreditCard(ModelMap model){
		CreditCard cc = new CreditCard();
		List<String> cardTypes = new ArrayList<String>();
		cardTypes.add("Visa");
		cardTypes.add("MasterCard");
		
		model.addAttribute("cardTypes", cardTypes);
		model.addAttribute("creditCard", cc);
		return "addCard";
	}
	
	@RequestMapping(value = "/addCard", method = RequestMethod.POST)
	public ModelAndView addAccount(@ModelAttribute("creditCard")CreditCard cc, ModelMap model){
		User user = (User) RequestContextHolder.currentRequestAttributes().getAttribute("user", RequestAttributes.SCOPE_SESSION);
		Account acc = accService.getAccount(user.getAccount().getId());
		cc.setAccount(acc);
		accService.addCreditCard(cc);
		accService.updateAccount(acc);
		model.addAttribute("account", acc);
		return new ModelAndView("redirect:/profile.html");
	}
	
	@RequestMapping(value = "/addAccount", method = RequestMethod.GET)
	public String showAddAccount(ModelMap model){
		Account acc = new Account();
		model.addAttribute("account", acc);
		return "addAccount";
	}
	
	@RequestMapping(value = "/addAccount", method = RequestMethod.POST)
	public ModelAndView addAccount(@ModelAttribute("account")Account acc, ModelMap model){
		User user = (User) RequestContextHolder.currentRequestAttributes().getAttribute("user", RequestAttributes.SCOPE_SESSION);
//		if(user.getAccount()==null){
//			user.setAccount(new Account());
//		}
		user.getAccount().setAddress(acc.getAddress());
		userService.updateUser(user);
		
		model.addAttribute("account", acc);
		model.put("user", user);
		return new ModelAndView("redirect:/addCard.html");
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView handleSearch(@ModelAttribute("myObjectList")String user){
		return null;
		
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView addUser(@ModelAttribute("command")  UserBean userBean, BindingResult result) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("users",  prepareListofBean(userService.userList()));
		return new ModelAndView("register", model);
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView welcome() {
		return new ModelAndView("index");
	}
	
	private User prepareModel(UserBean userBean){
		User user = new User();
		Account acc = new Account();
		acc.setAddress("");
		user.setUserName(userBean.getUserName());
		user.setPassword(userBean.getPassword());
		user.setAccount(acc);
		return user;
	}
	
	private List<UserBean> prepareListofBean(List<User> users){
		List<UserBean> beans = null;
		if(users != null && !users.isEmpty()){
			beans = new ArrayList<UserBean>();
			UserBean bean = null;
			for(User user : users){
				bean = new UserBean();
				bean.setPassword(user.getPassword());
				bean.setId(user.getId());
				bean.setUserName(user.getUserName());
				beans.add(bean);
			}
		}
		return beans;
	}
}

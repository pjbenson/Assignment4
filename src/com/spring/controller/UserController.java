package com.spring.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;

import com.spring.bean.AccountBean;
import com.spring.bean.UserBean;
import com.spring.model.Account;
import com.spring.model.Role;
import com.spring.model.Stock;
import com.spring.model.User;
import com.spring.service.AccountService;
import com.spring.service.LoginService;
import com.spring.service.RoleService;
import com.spring.service.StockService;
import com.spring.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private LoginService loginService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private StockService stockService;
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveUser(@ModelAttribute("command") UserBean userBean, BindingResult result) {
		User user = prepareModel(userBean);
		User checkIfUserExists = loginService.checkLogin(user.getUserName(), user.getPassword());
		Role role_user = roleService.getRole(2);
		if(checkIfUserExists == null){
			user.setRole(role_user);
			userService.addUser(user);
			return new ModelAndView("redirect:/loginform.html");
		}
		else{
			return new ModelAndView("redirect:/register.html");
		}
	}
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView showProfile() {
		return new ModelAndView("profile");
	}
	
	@RequestMapping(value = "/adminProfile", method = RequestMethod.GET)
	public ModelAndView showAdminProfile(ModelMap model) {
		List<User> users = userService.userList();
		List<Stock> stock = stockService.getAllStock();
		
		model.addAttribute("users", users);
		model.addAttribute("stock", stock);
		return new ModelAndView("adminProfile");
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
	
	@RequestMapping(value = "/addAccount", method = RequestMethod.GET)
	public String showAddAccount(ModelMap model){
		Account acc = new Account();
		model.addAttribute("account", acc);
		return "addAccount";
	}
	
	@RequestMapping(value = "/addAccount", method = RequestMethod.POST)
	public ModelAndView addAccount(@ModelAttribute("account")Account acc, Map model){
		User user = (User) RequestContextHolder.currentRequestAttributes().getAttribute("user", RequestAttributes.SCOPE_SESSION);
		User u = userService.getUser(user.getId());
		u.getAccount().setAddress(acc.getAddress());
		userService.updateBalance(u);
		model.put("user", u);
		return new ModelAndView("redirect:/profile.html");
	}
	
//	@RequestMapping(value = "/updateBalance", method = RequestMethod.POST)
//	public ModelAndView updateBalance(@ModelAttribute("account") Account acc, BindingResult result) {
//		User user = (User) RequestContextHolder.currentRequestAttributes().getAttribute("user", RequestAttributes.SCOPE_SESSION);
//		User userToUpdate = userService.getUser(user.getId());
//		userService.updateBalance(userToUpdate);
//		return new ModelAndView("redirect:/index.html");
//	}
	
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
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView deleteUser(@ModelAttribute("command")  UserBean userBean, BindingResult result) {
		userService.deleteUser(prepareModel(userBean));
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("user", null);
		model.put("users",  prepareListofBean(userService.userList()));
		return new ModelAndView("index", model);
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

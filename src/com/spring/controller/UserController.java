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
import com.spring.model.User;
import com.spring.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveUser(@ModelAttribute("command") UserBean userBean, BindingResult result) {
		User user = prepareModel(userBean);
		userService.addUser(user);
		return new ModelAndView("redirect:/loginform.html");
	}
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView showProfile() {
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
//		Account acc = new Account();
//		acc.setBalance(0.0);
		User user = new User();
		user.setUserName(userBean.getUserName());
		user.setPassword(userBean.getPassword());
		user.setId(userBean.getId());
//		user.setAccount(acc);
		userBean.setId(null);
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
	
	private UserBean prepareUserBean(User user){
		UserBean bean = new UserBean();
		bean.setId(user.getId());
		bean.setPassword(user.getPassword());
		bean.setUserName(user.getUserName());
		
		return bean;
	}
}

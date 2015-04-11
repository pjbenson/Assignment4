package com.spring.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.spring.model.User;
import com.spring.service.LoginService;

@Controller
@SessionAttributes({"user", "cart"})
@RequestMapping("loginform.html")
public class LoginController {

	@Autowired
	public LoginService loginService;

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET)
	public String showForm(ModelMap model) {
		User user = new User();
		model.addAttribute(user);
		return "loginform";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView processForm(@ModelAttribute("user") User user, BindingResult result, Map model, HttpSession sessionObj) {

		if (result.hasErrors()) {
			return new ModelAndView("redirect:/loginform.html");
		}
		
		User currentUser = loginService.checkLogin(user.getUserName(), user.getPassword());
		if(currentUser != null && currentUser.getRole().getRole().equals("ROLE_USER")){
			model.put("user", currentUser);
			return new ModelAndView("redirect:/profile.html");
		}
		else if(currentUser != null && currentUser.getRole().getRole().equals("ROLE_ADMIN")){
			model.put("user", currentUser);
			return new ModelAndView("redirect:/adminProfile.html");
		}
		else{
			result.rejectValue("userName","invaliduser");
			return new ModelAndView("redirect:/loginform.html");
		}
	}
}

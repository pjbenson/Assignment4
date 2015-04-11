package com.spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;

import com.spring.model.User;
import com.spring.service.LoginService;


@Controller
@SessionAttributes("cart")
public class LogOutController {

	@Autowired
	public LoginService loginService;

	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public ModelAndView logout(@ModelAttribute User user, HttpSession session, ModelMap model) {
		loginService.logOut();
		session.removeAttribute("user");
		session.removeAttribute("cart");
		return new ModelAndView("redirect:/index.html");
	}
}

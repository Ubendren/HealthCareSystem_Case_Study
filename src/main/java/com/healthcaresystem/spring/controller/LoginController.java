package com.healthcaresystem.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.healthcaresystem.spring.dao.LoginDao;
import com.healthcaresystem.spring.model.MemberLogin;


@Controller
public class LoginController {
	
	final static Logger logger = Logger.getLogger(com.healthcaresystem.spring.controller.LoginController.class);
	
	@Autowired
	LoginDao logindao;
	
	String validationresult;
	ModelAndView modelandview;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView LoginMethod(@ModelAttribute("memberlogin") MemberLogin memberlogin, HttpServletRequest request,
			HttpServletResponse response, HttpSession session){
		
		logger.info("This is Login Controller");
		int userid = Integer.parseInt(request.getParameter("inputUsername"));
		String password = request.getParameter("inputPassword");
		
		validationresult = logindao.ValidateLogin(userid,password);
		
		if(validationresult.equals("valid user"))
			modelandview = new ModelAndView("upload");
		else
			modelandview = new ModelAndView("index","errmsg","INVALID USERNAME OR PASSWORD");	
		
		return modelandview;
	}

}

package com.healthcaresystem.spring.controller;

import java.io.IOException;
import java.text.ParseException;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.healthcaresystem.spring.dao.CalculatePremiumDao;

@Controller
public class CalculatePremiumController {
	
	@Autowired
	CalculatePremiumDao calculatepremiumdao;

	@RequestMapping(value = "/calculatepremium")
	public void CalculatePremium() throws IOException, HibernateException, ParseException{
		
		calculatepremiumdao.CalculatePremium();
		
	}
}

package com.healthcaresystem.spring.controller;

import java.io.IOException;
import java.text.ParseException;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.healthcaresystem.spring.dao.CalculatePremiumDao;

@Controller
public class CalculatePremiumController {
	
	@Autowired
	CalculatePremiumDao calculatepremiumdao;
	
	ModelAndView modelandview;

	@RequestMapping(value = "/calculatepremium")
	public ModelAndView CalculatePremium() throws IOException, HibernateException, ParseException{
		try{
		calculatepremiumdao.CalculatePremium();
	//	calculatepremiumdao.GeneratePremiumMasterFile();
		modelandview = new ModelAndView("PremCalResult","successMsg","Premium Calculation is done successfully!!!");
		}
		catch(Exception e){
			e.printStackTrace();
			modelandview = new ModelAndView("PremCalResult","successMsg","Suspended due to Exception!!!");
		}
		
		
		
		return modelandview;
		
	}
}

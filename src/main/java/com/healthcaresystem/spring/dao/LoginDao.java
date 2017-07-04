package com.healthcaresystem.spring.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import Constants.Constant;

import com.healthcaresystem.spring.model.MemberLogin;
import com.healthcaresystem.spring.util.HibernateUtil;

public class LoginDao {

	SessionFactory sessionFactory;
	
	@Autowired
	MemberLogin memberlogin;
	String validationresult;
	
	Constant constant = new Constant();
	
	public String ValidateLogin(int userid, String password){
		
		
		sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		System.out.println(userid);
		System.out.println(password);
		
		
		String sql = constant.loginvalidationquery;
		Query validationquery = session.createSQLQuery(sql);
		validationquery.setParameter("userid", userid);
		List passwordlist = validationquery.list();
		System.out.println(passwordlist);
		
		if(!passwordlist.isEmpty()){
			if(passwordlist.get(0).equals(password)){
				validationresult = "valid user";
			}
			else
				validationresult = "invalid user";
		}
		else
			validationresult = "invalid user";	
		
		
			
		System.out.println(validationresult);
		session.close();
		return validationresult;
	}
}

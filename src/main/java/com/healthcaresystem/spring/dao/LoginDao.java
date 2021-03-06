package com.healthcaresystem.spring.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.healthcaresystem.spring.util.Constant;

import com.healthcaresystem.spring.model.MemberLogin;
import com.healthcaresystem.spring.util.HibernateUtil;

public class LoginDao {
	
	final static Logger logger = Logger.getLogger(com.healthcaresystem.spring.dao.LoginDao.class);
	SessionFactory sessionFactory;
	
	@Autowired
	MemberLogin memberlogin;
	String validationresult;
	
	
	
	public String ValidateLogin(String userid, String password){
		
		
		sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		logger.debug(userid);
		logger.debug(password);
		
		
		String sql = Constant.loginvalidationquery;
		Query validationquery = session.createSQLQuery(sql);
		validationquery.setParameter("userid", userid);
		List passwordlist = validationquery.list();
		logger.debug(passwordlist);
		
		if(!passwordlist.isEmpty()){
			if(passwordlist.get(0).equals(password)){
				validationresult = "valid user";
			}
			else
				validationresult = "invalid user";
		}
		else
			validationresult = "invalid user";	
		
		
			
		logger.debug(validationresult);
		session.close();
		return validationresult;
	}
}

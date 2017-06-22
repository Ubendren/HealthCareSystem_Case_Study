package com.healthcaresystem.spring.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.healthcaresystem.spring.model.MemberLogin;
import com.healthcaresystem.spring.util.HibernateUtilites;

public class LoginDao {

	SessionFactory sessionFactory;
	
	@Autowired
	MemberLogin memberlogin;
	String validationresult;
	
	public String ValidateLogin(int userid, String password){
		
		
		sessionFactory = HibernateUtilites.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		System.out.println(userid);
		System.out.println(password);
		/*String hql = "FROM MemberLogin";
		Query query = session.createQuery(hql);
		List memberloginlist = query.list();
		System.out.println(userid);
		System.out.println(memberloginlist);
		System.out.println(memberloginlist.contains(userid));
		if(memberloginlist.contains(userid)){
			String sql = "SELECT m.Password FROM memberlogin m WHERE m.UserId = :userid";
			Query validationquery = session.createSQLQuery(sql);
			validationquery.setParameter("userid", userid);
			List passwordlist = query.list();
			if(passwordlist.get(0).equals(password)){
				validationresult = "valid user";
			}
			else
				validationresult = "invalid user";
		}
		else
			validationresult = "invalid user";*/
		
		String sql = "SELECT m.Password FROM memberlogin m WHERE m.UserId = :userid";
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

package com.healthcaresystem.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="memberlogin")
public class MemberLogin {

	
	
	@Id @GeneratedValue
	@Column(name="UserId", nullable = false)
	private int UserId;
	
	@Column(name="Password")
	private String Password;
	
	
	@Column(name="RoleId")
	private int RoleId;
	
	@Column(name="RoleDescription")
	private String RoleDescription;

	public int getRoleId() {
		return RoleId;
	}


	public void setRoleId(int roleId) {
		RoleId = roleId;
	}


	public String getRoleDescription() {
		return RoleDescription;
	}


	public void setRoleDescription(String roleDescription) {
		RoleDescription = roleDescription;
	}


	public int getUserId() {
		return UserId;
	}


	public void setUserId(int userId) {
		UserId = userId;
	}


	public String getPassword() {
		return Password;
	}


	public void setPassword(String password) {
		Password = password;
	}


	public MemberLogin() {
		// TODO Auto-generated constructor stub
	}
	
	
}

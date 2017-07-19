package com.healthcaresystem.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="memberlogin")
public class MemberLogin {

	
	
	@Id
	@Column(name="UserId", nullable = false)
	private String UserId;
	
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


	public String getUserId() {
		return UserId;
	}


	public void setUserId(String userId) {
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

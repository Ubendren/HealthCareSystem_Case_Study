package com.healthcaresystem.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="premiumrejectdata")
public class PremiumRejectData {

	
	@Id
	@Column(name="Member_Id", nullable = false)
	private int Member_Id;
	
	@Column(name="Policy_Number")
	private String Policy_Number;
	
	@Column(name="Premium_Start_Date")
	private Date Premium_Start_Date ;
	
	@Column(name="Premium_End_Date")
	private  Date Premium_End_Date;
	
	@Column(name="Premium_Amount")
	private  double Premium_Amount ;
	
	@Column(name="Late_Fee")
	private  double Late_Fee;
	
	
	@Column(name="Process_Date")
	private Date Process_Date;
	
	@Column(name="Error_Description")
	private String Error_Description ;

	public String getPolicy_Number() {
		return Policy_Number;
	}

	public int getMember_Id() {
		return Member_Id;
	}

	public void setMember_Id(int member_Id) {
		Member_Id = member_Id;
	}

	public void setPolicy_Number(String policy_Number) {
		Policy_Number = policy_Number;
	}

	public Date getPremium_Start_Date() {
		return Premium_Start_Date;
	}

	public void setPremium_Start_Date(Date premium_Start_Date) {
		Premium_Start_Date = premium_Start_Date;
	}

	public Date getPremium_End_Date() {
		return Premium_End_Date;
	}

	public void setPremium_End_Date(Date premium_End_Date) {
		Premium_End_Date = premium_End_Date;
	}

	public double getPremium_Amount() {
		return Premium_Amount;
	}

	public void setPremium_Amount(double premium_Amount) {
		Premium_Amount = premium_Amount;
	}

	public double getLate_Fee() {
		return Late_Fee;
	}

	public void setLate_Fee(double late_Fee) {
		Late_Fee = late_Fee;
	}

	public Date getProcess_Date() {
		return Process_Date;
	}

	public void setProcess_Date(Date process_Date) {
		Process_Date = process_Date;
	}

	public String getError_Description() {
		return Error_Description;
	}

	public void setError_Description(String error_Description) {
		Error_Description = error_Description;
	}
	
	public PremiumRejectData() {
		// TODO Auto-generated constructor stub
	}
	
}

package com.healthcaresystem.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="premiumprocess")
public class PremiumProcess {
	
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

	
	public int getMember_Id() {
		return Member_Id;
	}

	public void setMember_Id(int member_Id) {
		Member_Id = member_Id;
	}

	public String getPolicy_Number() {
		return Policy_Number;
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
	
	public PremiumProcess() {
		// TODO Auto-generated constructor stub
	}
	
}

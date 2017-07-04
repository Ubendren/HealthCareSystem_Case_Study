package com.healthcaresystem.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="premiummaster")
public class PremiumMaster {
	
	
	@Id
	@Column(name="Member_Id", nullable = false)
		private int Member_Id;
	@Column(name="Policy_Number")
		private String Policy_Number;
	@Column(name="Sequence_Number")
		private int Sequence_Number;
	@Column(name="Premium_Start_Date")
		private Date Premium_Start_Date;
	@Column(name="Premium_End_Date")
		private Date Premium_End_Date;
	@Column(name="Premium_Amount")
		private double Premium_Amount;
	@Column(name="Late_Fee")
		private double Late_Fee;
	@Column(name="Premium_Paid_Date")
		private Date Premium_Paid_Date;
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
	public int getSequence_Number() {
		return Sequence_Number;
	}
	public void setSequence_Number(int sequence_Number) {
		Sequence_Number = sequence_Number;
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
	public Date getPremium_Paid_Date() {
		return Premium_Paid_Date;
	}
	public void setPremium_Paid_Date(Date premium_Paid_Date) {
		Premium_Paid_Date = premium_Paid_Date;
	}
	public PremiumMaster(int member_Id, String policy_Number,
			int sequence_Number, Date premium_Start_Date,
			Date premium_End_Date, double premium_Amount, double late_Fee,
			Date premium_Paid_Date) {
		super();
		Member_Id = member_Id;
		Policy_Number = policy_Number;
		Sequence_Number = sequence_Number;
		Premium_Start_Date = premium_Start_Date;
		Premium_End_Date = premium_End_Date;
		Premium_Amount = premium_Amount;
		Late_Fee = late_Fee;
		Premium_Paid_Date = premium_Paid_Date;
	}
	public PremiumMaster() {
		super();
	}
	
	
}

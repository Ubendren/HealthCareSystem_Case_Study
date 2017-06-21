package com.healthcaresystem.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="memberdata")
public class MemberData {


	@Id @GeneratedValue
	@Column(name="Member_Id", nullable = false)
		private int Member_Id;
	
	@Column(name="Policy_No" , nullable = false)
		private String Policy_No;
	
	@Column(name="Applicant_Full_Name")
		private String  Applicant_Full_Name; 
	
	@Column(name="Door_No")
		private int  Door_No ;
	
	@Column(name="Street")
		private String  Street;
	
	@Column(name="City")
		private String  City ;
	
	@Column(name="State_code")
		private String  State_code ;
	
	@Column(name="Country_Code")
		private String  Country_Code ;
	
	@Column(name="Zip_Code")
		private String Zip_Code;
	
	@Column(name="Cell_Phone_No")
		private String Cell_Phone_No;
	
	@Column(name="Date_of_Birth")
		private String  Date_of_Birth;
	
	@Column(name="Gender")
		private String Gender;
	
	@Column(name="SSN")
		private String SSN;

	@Column(name="Date_Policy_Applied")
		private String Date_Policy_Applied; 

	@Column(name="Student_Ind")
	private String Student_Ind; 
	
	@Column(name="Hazardous_Occupation")
		private String Hazardous_Occupation ;
	
	@Column(name="Heart_Disease_present")
		private String Heart_Disease_present ;
	
	@Column(name="Involved_in_Aviation_Activities")
		private String Involved_in_Aviation_Activities;
	
	@Column(name="Drinking_Smoking_Habits")
		private String Drinking_Smoking_Habits;
	
	@Column(name="Prem_Frequency")
		private int Prem_Frequency;
	
	@Column(name="Agent_Code")
		private String  Agent_Code ;
	
	
	@Column(name="Premium_Amount")
		private int Premium_Amount;
	
	@Column(name="Coverage_Amount")
		private double Coverage_Amount ;
	
	@Column(name="Deductable_Amount")
	private double Deductable_Amount ;
	
	public double getDeductable_Amount() {
		return Deductable_Amount;
	}
	public void setDeductable_Amount(double deductable_Amount) {
		Deductable_Amount = deductable_Amount;
	}

	@Column(name="Specialist_Visits_Allowed")
		private int Specialist_Visits_Allowed;
	
	@Column(name="Process_Date")
		private String Process_Date;
	
	@Column(name="Policy_Status")
		private String Policy_Status;
		
		public int getMember_Id() {
			return Member_Id;
		}
		public void setMember_Id(int member_Id) {
			Member_Id = member_Id;
		}
		public String getPolicy_No() {
			return Policy_No;
		}
		public void setPolicy_No(String policy_No) {
			Policy_No = policy_No;
		}
		public String getApplicant_Full_Name() {
			return Applicant_Full_Name;
		}
		public void setApplicant_Full_Name(String applicant_Full_Name) {
			Applicant_Full_Name = applicant_Full_Name;
		}
		public int getDoor_No() {
			return Door_No;
		}
		public void setDoor_No(int door_No) {
			Door_No = door_No;
		}
		public String getStreet() {
			return Street;
		}
		public void setStreet(String street) {
			Street = street;
		}
		public String getCity() {
			return City;
		}
		public void setCity(String city) {
			City = city;
		}
		public String getState_code() {
			return State_code;
		}
		public void setState_code(String state_code) {
			State_code = state_code;
		}
		public String getCountry_Code() {
			return Country_Code;
		}
		public void setCountry_Code(String country_Code) {
			Country_Code = country_Code;
		}
		public String getZip_Code() {
			return Zip_Code;
		}
		public void setZip_Code(String zip_Code) {
			Zip_Code = zip_Code;
		}
		public String getCell_Phone_No() {
			return Cell_Phone_No;
		}
		public void setCell_Phone_No(String cell_Phone_No) {
			Cell_Phone_No = cell_Phone_No;
		}
		public String getDate_of_Birth() {
			return Date_of_Birth;
		}
		public void setDate_of_Birth(String date_of_Birth) {
			Date_of_Birth = date_of_Birth;
		}
		public String getGender() {
			return Gender;
		}
		public void setGender(String gender) {
			Gender = gender;
		}
		public String getSSN() {
			return SSN;
		}
		public void setSSN(String sSN) {
			SSN = sSN;
		}
		
		public String getDate_Policy_Applied() {
			return Date_Policy_Applied;
		}
		public void setDate_Policy_Applied(String date_Policy_Applied) {
			Date_Policy_Applied = date_Policy_Applied;
		}
		public String getStudent_Ind() {
			return Student_Ind;
		}
		public void setStudent_Ind(String student_Ind) {
			Student_Ind = student_Ind;
		}
		public String getHazardous_Occupation() {
			return Hazardous_Occupation;
		}
		public void setHazardous_Occupation(String hazardous_Occupation) {
			Hazardous_Occupation = hazardous_Occupation;
		}
		public String getHeart_Disease_present() {
			return Heart_Disease_present;
		}
		public void setHeart_Disease_present(String heart_Disease_present) {
			Heart_Disease_present = heart_Disease_present;
		}
		public String getInvolved_in_Aviation_Activities() {
			return Involved_in_Aviation_Activities;
		}
		public void setInvolved_in_Aviation_Activities(String involved_in_Aviation_Activities) {
			Involved_in_Aviation_Activities = involved_in_Aviation_Activities;
		}
		public String getDrinking_Smoking_Habits() {
			return Drinking_Smoking_Habits;
		}
		public void setDrinking_Smoking_Habits(String drinking_Smoking_Habits) {
			Drinking_Smoking_Habits = drinking_Smoking_Habits;
		}
		public int getPrem_Frequency() {
			return Prem_Frequency;
		}
		public void setPrem_Frequency(int prem_Frequency) {
			Prem_Frequency = prem_Frequency;
		}
		public String getAgent_Code() {
			return Agent_Code;
		}
		public void setAgent_Code(String agent_Code) {
			Agent_Code = agent_Code;
		}
		
		public int getPremium_Amount() {
			return Premium_Amount;
		}
		public void setPremium_Amount(int premium_Amount) {
			Premium_Amount = premium_Amount;
		}
		public double getCoverage_Amount() {
			return Coverage_Amount;
		}
		public void setCoverage_Amount(double coverage_Amount) {
			Coverage_Amount = coverage_Amount;
		}
		public int getSpecialist_Visits_Allowed() {
			return Specialist_Visits_Allowed;
		}
		public void setSpecialist_Visits_Allowed(int specialist_Visits_Allowed) {
			Specialist_Visits_Allowed = specialist_Visits_Allowed;
		}
		public String getProcess_Date() {
			return Process_Date;
		}
		public void setProcess_Date(String process_Date) {
			Process_Date = process_Date;
		}
		public String getPolicy_Status() {
			return Policy_Status;
		}
		public void setPolicy_Status(String policy_Status) {
			Policy_Status = policy_Status;
		}
		
		public MemberData() {
			// TODO Auto-generated constructor stub
		}
		
		
}

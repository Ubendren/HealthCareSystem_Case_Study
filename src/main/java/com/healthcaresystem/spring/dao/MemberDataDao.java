package com.healthcaresystem.spring.dao;
import com.healthcaresystem.spring.util.HibernateUtilites;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.healthcaresystem.spring.model.FailedMemberData;
import com.healthcaresystem.spring.model.MemberData;

public class MemberDataDao {
	FailedMemberData failedmemberdata = new FailedMemberData();
	
	SessionFactory sessionFactory;
	
	String name;
	int doorno;
	String street;
	String city;
	String statecode;
	String countrycode;
	String zipcode;
	String cellphoneno;
	Date dateofbirth;
	char gender;
	String ssn;
	Date policyapplieddate;
	char studentind;
	char hazardousoccupation;
	char heartdisease;
	char aviationactivities;
	char drinkingsmoking;
	int premiumfrequency;
	String agentcode;
	double coverageamount;
	double deductibleamount;
	int count;
	
	
	public void ValidateExcelData(MemberData memberdata){
		
		name = memberdata.getApplicant_Full_Name().trim();
		doorno = memberdata.getDoor_No(); 
		street = memberdata.getStreet().trim();
		city = memberdata.getCity().trim();
		statecode = memberdata.getState_code().trim();
		countrycode = memberdata.getCountry_Code().trim();
		zipcode = memberdata.getZip_Code().trim();
		cellphoneno = memberdata.getCell_Phone_No().trim();
		dateofbirth = memberdata.getDate_of_Birth();
		gender = memberdata.getGender();
		ssn = memberdata.getSSN().trim();
		policyapplieddate = memberdata.getDate_Policy_Applied();
		studentind = memberdata.getStudent_Ind();
		hazardousoccupation = memberdata.getHazardous_Occupation();
		heartdisease = memberdata.getHeart_Disease_present();
		aviationactivities = memberdata.getInvolved_in_Aviation_Activities();
		drinkingsmoking = memberdata.getDrinking_Smoking_Habits();
		premiumfrequency = memberdata.getPrem_Frequency();
		agentcode = memberdata.getAgent_Code();
		coverageamount = memberdata.getCoverage_Amount();
		deductibleamount = memberdata.getDeductible_Amount();
		count = 0;
		
		count = Validations(name,doorno,street,city,statecode,countrycode,zipcode,cellphoneno,dateofbirth,gender,
				ssn,policyapplieddate,studentind,hazardousoccupation,heartdisease,aviationactivities,drinkingsmoking, 
				premiumfrequency,agentcode,coverageamount,deductibleamount,memberdata);
		
		if(count == 20)
			ValidationSuccess(memberdata);
		
	}
	
	
	public int Validations(String name,int doorno,String street,String city,String statecode,String countrycode,
			String zipcode,String cellphoneno,Date dateofbirth,char gender,String ssn,Date policyapplieddate,
			char studentind,char hazardousoccupation,char heartdisease,char aviationactivities,char drinkingsmoking, 
			int premiumfrequency,String agentcode,double coverageamount,double deductibleamount, MemberData memberdata){
		
		if(name.length()>=5 && name.length()<=30 && Character.isAlphabetic(name.charAt(0))){
			count++;
		}
		else{
			ValidationFailure("Applicant Name field not met the requirements", memberdata);
			return 0;
		}
		
		if(doorno>10000 && doorno<99999){
			count++;
		}
		else{
			ValidationFailure("Door Number field not met the requirements", memberdata);
			return 0;
		}
		if(street.length()>=3 && street.length()<=30 && Character.isAlphabetic(street.charAt(0))){
			count++;
		}
		else{
			ValidationFailure("Street field not met the requirements", memberdata);
			return 0;
		}
		if(city.length()>=3 && city.length()<=20 && Character.isAlphabetic(city.charAt(0))){
			count++;
		}
		else{
			ValidationFailure("City field not met the requirements", memberdata);
			return 0;
		}
		if(statecode.length()==2 && Character.isAlphabetic(statecode.charAt(0))){
			count++;
		}
		else{
			ValidationFailure("State Code field not met the requirements", memberdata);
			return 0;
		}
		if(countrycode.equals("USA")){
			count++;
		}
		else{
			ValidationFailure("Country Code field not met the requirements", memberdata);
			return 0;
		}
		if(Pattern.matches("^[0-9]{5}$|([0-9]{5}(-[0-9]{4}))?", zipcode)){
			count++;
		}
		else{
			ValidationFailure("Zip Code field not met the requirements", memberdata);
			return 0;
		}
		if(Pattern.matches("^[0-9]{3}-[0-9]{3}-[0-9]{4}$", cellphoneno)){
			count++;
		}
		else{
			ValidationFailure("CellPhone Number field not met the requirements", memberdata);
			return 0;
		}
		if(gender == 'M' || gender == 'F' || gender == 'U'){
			count++;
		}
		else{
			ValidationFailure("Gender field not met the requirements", memberdata);
			return 0;
		}
		if(Pattern.matches("^[0-9]{3}-[0-9]{2}-[0-9]{4}$", ssn)){
			count++;
		}
		else{
			ValidationFailure("SSN field not met the requirements", memberdata);
			return 0;
		}
		if(studentind == 'Y' || studentind == 'N' || studentind == ' '){
			count++;
		}
		else{
			ValidationFailure("Student Identity field not met the requirements", memberdata);
			return 0;
		}
		if(hazardousoccupation == 'Y' || hazardousoccupation == 'N' || hazardousoccupation == ' '){
			count++;
		}
		else{
			ValidationFailure("Hazardous Occupation field not met the requirements", memberdata);
			return 0;
		}
		if(heartdisease == 'Y' || heartdisease == 'N' || heartdisease == ' '){
			count++;
		}
		else{
			ValidationFailure("Heart Disease field not met the requirements", memberdata);
			return 0;
		}
		if(aviationactivities == 'Y' || aviationactivities == 'N' || aviationactivities == ' '){
			count++;
		}
		else{
			ValidationFailure("Involved in Aviation Activities field not met the requirements", memberdata);
			return 0;
		}
		if(drinkingsmoking == 'Y' || drinkingsmoking == 'N' || drinkingsmoking == ' '){
			count++;
		}
		else{
			ValidationFailure("Drinking/Smoking Habits field not met the requirements", memberdata);
			return 0;
		}
		if(premiumfrequency == 1 || premiumfrequency == 2 || premiumfrequency == 4 || premiumfrequency == 12){
			count++;
		}
		else{
			ValidationFailure("Premium Frequency field not met the requirements", memberdata);
			return 0;
		}
		if(coverageamount>=50000 && coverageamount<=500000){
			count++;
		}
		else{
			ValidationFailure("Premium Frequency field not met the requirements", memberdata);
			return 0;
		}
		if(deductibleamount<= (50*coverageamount)/100){
			count++;
		}
		else{
			ValidationFailure("Premium Frequency field not met the requirements", memberdata);
			return 0;
		}
		
		if(agentcode.length()==6 && (agentcode.substring(0, 3).equals("AGA") || agentcode.substring(0, 3).equals("AGB")
				|| agentcode.substring(0, 3).equals("AGC"))){
			count++;
		}
		else{
			ValidationFailure("Agent Code field not met the requirements", memberdata);
			return 0;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/YYYY");
		sdf.setLenient(false);
		String DOB = dateofbirth.toString();
		String PAD = policyapplieddate.toString();
		try{
			sdf.parse(DOB);
			sdf.parse(PAD);
			count++;
		}
		catch(Exception E){
			ValidationFailure("Date fields not met the requirements", memberdata);
			return 0;
		}
		
		return count;
	}
	
	public void ValidationSuccess(MemberData memberdata){
		
			System.out.println("The validation is success");
			
			sessionFactory = HibernateUtilites.getSessionFactory();
			Session session = sessionFactory.openSession();
			org.hibernate.Transaction transaction = session.beginTransaction();
			
			Date sdf = null;
			try {
				sdf = new SimpleDateFormat("yyyy/MM/dd").parse(new Date().toString());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String policy_No = sdf.toString()+"0000";
			memberdata.setPolicy_No(policy_No);
			session.save(memberdata);
			
			transaction.commit();
			session.close();
			
	
	}
	
	
	public void ValidationFailure(String remarks, MemberData memberdata){
		
		System.out.println("The validation is failure with the reason "+remarks);
		
		sessionFactory = HibernateUtilites.getSessionFactory();
		Session session = sessionFactory.openSession();
		org.hibernate.Transaction transaction = session.beginTransaction();
		
		failedmemberdata.setApplicant_Full_Name(memberdata.getApplicant_Full_Name());
		failedmemberdata.setDOB(memberdata.getDate_of_Birth().toString());
		failedmemberdata.setSSN(memberdata.getSSN());
		failedmemberdata.setError_Description(remarks);
		failedmemberdata.setField_in_Error(remarks);
		session.save(failedmemberdata);
		
		transaction.commit();
		session.close();
	}
	
}

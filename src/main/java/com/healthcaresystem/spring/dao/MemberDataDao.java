package com.healthcaresystem.spring.dao;
import com.healthcaresystem.spring.util.HibernateUtilites;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.healthcaresystem.spring.model.FailedMemberData;
import com.healthcaresystem.spring.model.MasterMember;
import com.healthcaresystem.spring.model.MemberData;

public class MemberDataDao {
	FailedMemberData failedmemberdata = new FailedMemberData();
	MasterMember mastermember = new MasterMember();
	
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
	
	
	String filename;
	int totalrecords;
	int processedrecords;
	int failurerecords;
	
	public void ValidateExcelData(String filenames, int totalrecord, MemberData memberdata) throws IOException{
		
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
		
		 processedrecords = GenerateEnrolledExcel();
		 failurerecords = GenerateFailureExcel();
		filename = filenames;
		totalrecords = totalrecord;
		
		
	}
	
	
	public int Validations(String name,int doorno,String street,String city,String statecode,String countrycode,
			String zipcode,String cellphoneno,Date dateofbirth,char gender,String ssn,Date policyapplieddate,
			char studentind,char hazardousoccupation,char heartdisease,char aviationactivities,char drinkingsmoking, 
			int premiumfrequency,String agentcode,double coverageamount,double deductibleamount, MemberData memberdata){
		
		if(name.length()>=5 && name.length()<=30 && Character.isAlphabetic(name.charAt(0))){
			count++;
		}
		else{
			ValidationFailure("Applicant Name","Applicant Name field not met the requirements", memberdata);
			return 0;
		}
		
		if(doorno>10000 && doorno<99999){
			count++;
		}
		else{
			ValidationFailure("Door Number","Door Number field not met the requirements", memberdata);
			return 0;
		}
		if(street.length()>=3 && street.length()<=30 && Character.isAlphabetic(street.charAt(0))){
			count++;
		}
		else{
			ValidationFailure("Street", "Street field not met the requirements", memberdata);
			return 0;
		}
		if(city.length()>=3 && city.length()<=20 && Character.isAlphabetic(city.charAt(0))){
			count++;
		}
		else{
			ValidationFailure("City", "City field not met the requirements", memberdata);
			return 0;
		}
		if(statecode.length()==2 && Character.isAlphabetic(statecode.charAt(0))){
			count++;
		}
		else{
			ValidationFailure("State Code", "State Code field not met the requirements", memberdata);
			return 0;
		}
		if(countrycode.equals("USA")){
			count++;
		}
		else{
			ValidationFailure("Country Code", "Country Code field not met the requirements", memberdata);
			return 0;
		}
		if(Pattern.matches("^[0-9]{5}$|([0-9]{5}(-[0-9]{4}))?", zipcode)){
			count++;
		}
		else{
			ValidationFailure("Zip Code", "Zip Code field not met the requirements", memberdata);
			return 0;
		}
		if(Pattern.matches("^[0-9]{3}-[0-9]{3}-[0-9]{4}$", cellphoneno)){
			count++;
		}
		else{
			ValidationFailure("CellPhone Number", "CellPhone Number field not met the requirements", memberdata);
			return 0;
		}
		if(gender == 'M' || gender == 'F' || gender == 'U'){
			count++;
		}
		else{
			ValidationFailure("Gender", "Gender field not met the requirements", memberdata);
			return 0;
		}
		if(Pattern.matches("^[0-9]{3}-[0-9]{2}-[0-9]{4}$", ssn)){
			count++;
		}
		else{
			ValidationFailure("SSN", "SSN field not met the requirements", memberdata);
			return 0;
		}
		if(studentind == 'Y' || studentind == 'N' || studentind == ' '){
			count++;
		}
		else{
			ValidationFailure("Student Identity", "Student Identity field not met the requirements", memberdata);
			return 0;
		}
		if(hazardousoccupation == 'Y' || hazardousoccupation == 'N' || hazardousoccupation == ' '){
			count++;
		}
		else{
			ValidationFailure("Hazardous Occupation", "Hazardous Occupation field not met the requirements", memberdata);
			return 0;
		}
		if(heartdisease == 'Y' || heartdisease == 'N' || heartdisease == ' '){
			count++;
		}
		else{
			ValidationFailure("Heart Disease", "Heart Disease field not met the requirements", memberdata);
			return 0;
		}
		if(aviationactivities == 'Y' || aviationactivities == 'N' || aviationactivities == ' '){
			count++;
		}
		else{
			ValidationFailure("Involved in Aviation Activities", "Involved in Aviation Activities field not met the requirements", memberdata);
			return 0;
		}
		if(drinkingsmoking == 'Y' || drinkingsmoking == 'N' || drinkingsmoking == ' '){
			count++;
		}
		else{
			ValidationFailure("Drinking/Smoking Habits", "Drinking/Smoking Habits field not met the requirements", memberdata);
			return 0;
		}
		if(premiumfrequency == 1 || premiumfrequency == 2 || premiumfrequency == 4 || premiumfrequency == 12){
			count++;
		}
		else{
			ValidationFailure("Premium Frequency", "Premium Frequency field not met the requirements", memberdata);
			return 0;
		}
		if(coverageamount>=50000 && coverageamount<=500000){
			count++;
		}
		else{
			ValidationFailure("Coverage Amount", "Coverage Amount field not met the requirements", memberdata);
			return 0;
		}
		if(deductibleamount<= (50*coverageamount)/100){
			count++;
		}
		else{
			ValidationFailure("Deductible Amount", "Deductible Amount field not met the requirements", memberdata);
			return 0;
		}
		
		if(agentcode.length()==6 && (agentcode.substring(0, 3).equals("AGA") || agentcode.substring(0, 3).equals("AGB")
				|| agentcode.substring(0, 3).equals("AGC"))){
			count++;
		}
		else{
			ValidationFailure("Agent Code", "Agent Code field not met the requirements", memberdata);
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
			ValidationFailure("Date fields", "Date fields not met the requirements", memberdata);
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
			memberdata.setPolicy_Status("H");
			memberdata.setProcess_Date(sdf);
			session.save(memberdata);
			
			transaction.commit();
			session.close();
			
	
	}
	
	
	public void ValidationFailure(String failedfield, String remarks, MemberData memberdata){
		
		System.out.println("The validation is failure with the reason "+remarks);
		
		sessionFactory = HibernateUtilites.getSessionFactory();
		Session session = sessionFactory.openSession();
		org.hibernate.Transaction transaction = session.beginTransaction();
		
		failedmemberdata.setApplicant_Full_Name(memberdata.getApplicant_Full_Name());
		failedmemberdata.setDOB(memberdata.getDate_of_Birth().toString());
		failedmemberdata.setSSN(memberdata.getSSN());
		failedmemberdata.setError_Description(remarks);
		failedmemberdata.setField_in_Error(failedfield);
		session.save(failedmemberdata);
		
		transaction.commit();
		session.close();
	}
	
	public int GenerateEnrolledExcel() throws IOException{
		sessionFactory = HibernateUtilites.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		String hql = "FROM MemberData";
		Query query = session.createQuery(hql);
		List<MemberData> memberdatalist = query.list();
		session.close();
		if(!memberdatalist.isEmpty()){
		XSSFWorkbook workbook = new XSSFWorkbook(); 
	      XSSFSheet spreadsheet = workbook
	      .createSheet("Enrolled Member Data");
	      XSSFRow row=spreadsheet.createRow(0);
	      XSSFCell cell;
	      cell=row.createCell(0);
	      cell.setCellValue("Member ID");
	      cell=row.createCell(1);
	      cell.setCellValue("Policy Number");
	      cell=row.createCell(2);
	      cell.setCellValue("Applicant Full Name");
	      cell=row.createCell(3);
	      cell.setCellValue("Door No");
	      cell=row.createCell(4);
	      cell.setCellValue("Street");
	      cell=row.createCell(5);
	      cell.setCellValue("City");
	      cell=row.createCell(6);
	      cell.setCellValue("State Code");
	      cell=row.createCell(7);
	      cell.setCellValue("Country Code");
	      cell=row.createCell(8);
	      cell.setCellValue("Zip Code");
	      cell=row.createCell(9);
	      cell.setCellValue("Cell Phone No");
	      cell=row.createCell(10);
	      cell.setCellValue("Date of Birth");
	      cell=row.createCell(11);
	      cell.setCellValue("Gender");
	      cell=row.createCell(12);
	      cell.setCellValue("SSN");
	      cell=row.createCell(13);
	      cell.setCellValue("Date Policy Applied");
	      cell=row.createCell(14);
	      cell.setCellValue("Student Ind");
	      cell=row.createCell(15);
	      cell.setCellValue("Hazardous Occupation");
	      cell=row.createCell(16);
	      cell.setCellValue("Heart Disease present");
	      cell=row.createCell(17);
	      cell.setCellValue("Involved in Aviation Activities");
	      cell=row.createCell(18);
	      cell.setCellValue("Drinking/Smoking Habits");
	      cell=row.createCell(19);
	      cell.setCellValue("Premium Frequency");
	      cell=row.createCell(20);
	      cell.setCellValue("Agent Code");
	      cell=row.createCell(21);
	      cell.setCellValue("Coverage Amount");
	      cell=row.createCell(22);
	      cell.setCellValue("Policy Status");
	     
	     
	      
	      int i=1;
	     for(MemberData m: memberdatalist)
	      {
	         row=spreadsheet.createRow(i);
	         cell=row.createCell(0);
	         cell.setCellValue(m.getMember_Id());
	         cell=row.createCell(1);
	         cell.setCellValue(m.getPolicy_No());
	         cell=row.createCell(2);
	         cell.setCellValue(m.getApplicant_Full_Name());
	         cell=row.createCell(3);
	         cell.setCellValue(m.getDoor_No());
	         cell=row.createCell(4);
	         cell.setCellValue(m.getStreet());
	         cell=row.createCell(5);
	         cell.setCellValue(m.getCity());
	         cell=row.createCell(6);
	         cell.setCellValue(m.getState_code());
	         cell=row.createCell(7);
	         cell.setCellValue(m.getCountry_Code());
	         cell=row.createCell(8);
	         cell.setCellValue(m.getZip_Code());
	         cell=row.createCell(9);
	         cell.setCellValue(m.getCell_Phone_No());
	         cell=row.createCell(10);
	         cell.setCellValue(m.getDate_of_Birth());
	         cell=row.createCell(11);
	         cell.setCellValue(m.getGender());
	         cell=row.createCell(12);
	         cell.setCellValue(m.getSSN());
	         cell=row.createCell(13);
	         cell.setCellValue(m.getDate_Policy_Applied());
	         cell=row.createCell(14);
	         cell.setCellValue(m.getStudent_Ind());
	         cell=row.createCell(15);
	         cell.setCellValue(m.getHazardous_Occupation());
	         cell=row.createCell(16);
	         cell.setCellValue(m.getHeart_Disease_present());
	         cell=row.createCell(17);
	         cell.setCellValue(m.getInvolved_in_Aviation_Activities());
	         cell=row.createCell(18);
	         cell.setCellValue(m.getDrinking_Smoking_Habits());
	         cell=row.createCell(19);
	         cell.setCellValue(m.getPrem_Frequency());
	         cell=row.createCell(20);
	         cell.setCellValue(m.getAgent_Code());
	         cell=row.createCell(21);
	         cell.setCellValue(m.getCoverage_Amount());
	         cell=row.createCell(22);
	         cell.setCellValue(m.getDeductible_Amount());
	         i++;
	      }
	      FileOutputStream out = new FileOutputStream(
	      new File("D:/Enrollment Process output folder/enrolledmemberdata.xlsx"));
	      workbook.write(out);
	      out.close();
	      System.out.println(
	      "enrolledmemberdata.xlsx written successfully");
		}
		return memberdatalist.size();
		
		
	}
	
	public int GenerateFailureExcel() throws IOException{
		sessionFactory = HibernateUtilites.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		String hql = "FROM FailedMemberData";
		Query query = session.createQuery(hql);
		List<FailedMemberData> failedmemberdatalist = query.list();
		
		session.close();
		
		if(!failedmemberdatalist.isEmpty())
		{
		XSSFWorkbook workbook = new XSSFWorkbook(); 
	      XSSFSheet spreadsheet = workbook
	      .createSheet("Failed Member Data");
	      XSSFRow row=spreadsheet.createRow(0);
	      XSSFCell cell;
	      cell=row.createCell(0);
	      cell.setCellValue("SSN");
	      cell=row.createCell(1);
	      cell.setCellValue("Date of Birth");
	      cell=row.createCell(2);
	      cell.setCellValue("Applicant Full Name");
	      cell=row.createCell(3);
	      cell.setCellValue("Field in Error");
	      cell=row.createCell(4);
	      cell.setCellValue("Error Description");
	      
	      int i=1;
		     for(FailedMemberData f: failedmemberdatalist)
		      {
		         row=spreadsheet.createRow(i);
		         cell=row.createCell(0);
		         cell.setCellValue(f.getSSN());
		         cell=row.createCell(1);
		         cell.setCellValue(f.getDOB());
		         cell=row.createCell(2);
		         cell.setCellValue(f.getApplicant_Full_Name());
		         cell=row.createCell(3);
		         cell.setCellValue(f.getField_in_Error());
		         cell=row.createCell(4);
		         cell.setCellValue(f.getError_Description());
		         i++;
		      }
		      FileOutputStream out = new FileOutputStream(
		      new File("D:/Enrollment Process output folder/failedmemberdata.xlsx"));
		      workbook.write(out);
		      out.close();
		      System.out.println(
		      "failedmemberdata.xlsx written successfully");
		}
		return failedmemberdatalist.size();
	      
	}
	
	public void CallUpdateMasterMemberData(){
		UpdateMasterMemberData(filename,totalrecords,processedrecords,failurerecords);
	}
	
	public void UpdateMasterMemberData(String filename, int totalrecords, int processedrecords, int failurerecords){
		sessionFactory = HibernateUtilites.getSessionFactory();
		Session session = sessionFactory.openSession();
		org.hibernate.Transaction transaction = session.beginTransaction();
		
		mastermember.setFile_Name(filename);
		mastermember.setProcessed_Records(processedrecords);
		mastermember.setTotal_Records(totalrecords);
		mastermember.setFailed_Records(failurerecords);
		mastermember.setDate_Time(new Date());
		session.save(mastermember);
		
		transaction.commit();
		session.close();
		
	}
	
}

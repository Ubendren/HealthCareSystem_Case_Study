package com.healthcaresystem.spring.dao;
import com.healthcaresystem.spring.util.Constant;

import com.healthcaresystem.spring.util.HibernateUtil;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
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
	
	final static Logger logger = Logger.getLogger(com.healthcaresystem.spring.dao.MemberDataDao.class);
	
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
	int policynumberfactor;
	
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
		//processedrecords = 0;
		//failurerecords = 0;
		
		count = Validations(name,doorno,street,city,statecode,countrycode,zipcode,cellphoneno,dateofbirth,gender,
				ssn,policyapplieddate,studentind,hazardousoccupation,heartdisease,aviationactivities,drinkingsmoking, 
				premiumfrequency,agentcode,coverageamount,deductibleamount,memberdata);
		
		if(count == 20)
		{	processedrecords++;
			ValidationSuccess(memberdata);
		}
		
		// processedrecords = GenerateEnrolledExcel();
		// failurerecords = GenerateFailureExcel();
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
			failurerecords++;
			return 0;
		}
		
		if(doorno>10000 && doorno<99999){
			count++;
		}
		else{
			ValidationFailure("Door Number","Door Number field not met the requirements", memberdata);
			failurerecords++;
			return 0;
		}
		if(street.length()>=3 && street.length()<=30 && Character.isAlphabetic(street.charAt(0))){
			count++;
		}
		else{
			ValidationFailure("Street", "Street field not met the requirements", memberdata);
			failurerecords++;
			return 0;
		}
		if(city.length()>=3 && city.length()<=20 && Character.isAlphabetic(city.charAt(0))){
			count++;
		}
		else{
			ValidationFailure("City", "City field not met the requirements", memberdata);
			failurerecords++;
			return 0;
		}
		if(statecode.length()==2 && Character.isAlphabetic(statecode.charAt(0))){
			count++;
		}
		else{
			ValidationFailure("State Code", "State Code field not met the requirements", memberdata);
			failurerecords++;
			return 0;
		}
		if(countrycode.equals("USA")){
			count++;
		}
		else{
			ValidationFailure("Country Code", "Country Code field not met the requirements", memberdata);
			failurerecords++;
			return 0;
		}
		if(Pattern.matches("^[0-9]{5}$|([0-9]{5}(-[0-9]{4}))?", zipcode)){
			count++;
		}
		else{
			ValidationFailure("Zip Code", "Zip Code field not met the requirements", memberdata);
			failurerecords++;
			return 0;
		}
		if(Pattern.matches("^[0-9]{3}-[0-9]{3}-[0-9]{4}$", cellphoneno)){
			count++;
		}
		else{
			ValidationFailure("CellPhone Number", "CellPhone Number field not met the requirements", memberdata);
			failurerecords++;
			return 0;
		}
		if(gender == 'M' || gender == 'F' || gender == 'U'){
			count++;
		}
		else{
			ValidationFailure("Gender", "Gender field not met the requirements", memberdata);
			failurerecords++;
			return 0;
		}
		if(Pattern.matches("^[0-9]{3}-[0-9]{2}-[0-9]{4}$", ssn)){
			count++;
		}
		else{
			ValidationFailure("SSN", "SSN field not met the requirements", memberdata);
			failurerecords++;
			return 0;
		}
		if(studentind == 'Y' || studentind == 'N' || studentind == ' '){
			count++;
		}
		else{
			ValidationFailure("Student Identity", "Student Identity field not met the requirements", memberdata);
			failurerecords++;
			return 0;
		}
		if(hazardousoccupation == 'Y' || hazardousoccupation == 'N' || hazardousoccupation == ' '){
			count++;
		}
		else{
			ValidationFailure("Hazardous Occupation", "Hazardous Occupation field not met the requirements", memberdata);
			failurerecords++;
			return 0;
		}
		if(heartdisease == 'Y' || heartdisease == 'N' || heartdisease == ' '){
			count++;
		}
		else{
			ValidationFailure("Heart Disease", "Heart Disease field not met the requirements", memberdata);
			failurerecords++;
			return 0;
		}
		if(aviationactivities == 'Y' || aviationactivities == 'N' || aviationactivities == ' '){
			count++;
		}
		else{
			ValidationFailure("Involved in Aviation Activities", "Involved in Aviation Activities field not met the requirements", memberdata);
			failurerecords++;
			return 0;
		}
		if(drinkingsmoking == 'Y' || drinkingsmoking == 'N' || drinkingsmoking == ' '){
			count++;
		}
		else{
			ValidationFailure("Drinking/Smoking Habits", "Drinking/Smoking Habits field not met the requirements", memberdata);
			failurerecords++;
			return 0;
		}
		if(premiumfrequency == 1 || premiumfrequency == 2 || premiumfrequency == 4 || premiumfrequency == 12){
			count++;
		}
		else{
			ValidationFailure("Premium Frequency", "Premium Frequency field not met the requirements", memberdata);
			failurerecords++;
			return 0;
		}
		if(coverageamount>=50000 && coverageamount<=500000){
			count++;
		}
		else{
			ValidationFailure("Coverage Amount", "Coverage Amount field not met the requirements", memberdata);
			failurerecords++;
			return 0;
		}
		if(deductibleamount<= (50*coverageamount)/100){
			count++;
		}
		else{
			ValidationFailure("Deductible Amount", "Deductible Amount field not met the requirements", memberdata);
			failurerecords++;
			return 0;
		}
		
		if(agentcode.length()==6 && (agentcode.substring(0, 3).equals("AGA") || agentcode.substring(0, 3).equals("AGB")
				|| agentcode.substring(0, 3).equals("AGC"))){
			count++;
		}
		else{
			ValidationFailure("Agent Code", "Agent Code field not met the requirements", memberdata);
			failurerecords++;
			return 0;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/YYYY");
		sdf.setLenient(false);
		String DOB = dateofbirth.toString();
		String PAD = policyapplieddate.toString();
		
		try{
			DateFormat format = new SimpleDateFormat("E MMM dd HH:mm:ss Z YYYY");
			Date dateDOB = (Date)format.parse(DOB);
			Date datePAD = (Date)format.parse(PAD);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dateDOB);
			String DateOfBirth = calendar.get(Calendar.MONTH)+"/"+calendar.get(Calendar.DATE)+"/"+calendar.get(Calendar.YEAR); 
			calendar.setTime(datePAD);
			String PolicyAppliedDate = calendar.get(Calendar.MONTH)+"/"+calendar.get(Calendar.DATE)+"/"+calendar.get(Calendar.YEAR);
			sdf.parse(DateOfBirth);
			sdf.parse(PolicyAppliedDate);
			count++;
		}
		catch(Exception E){
			ValidationFailure("Date fields", "Date fields not met the requirements", memberdata);
			failurerecords++;
			return 0;
		}
		
		return count;
	}
	
	public void ValidationSuccess(MemberData memberdata){
		
			logger.info("The validation is success");
			
			sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			org.hibernate.Transaction transaction = session.beginTransaction();
			
			
			
			
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			
			Query query = session.createSQLQuery("SELECT count(*) from memberdata");
			if(Integer.parseInt(query.list().get(0).toString()) == 0)
				policynumberfactor = 0;
			else
				policynumberfactor = Integer.parseInt(query.list().get(0).toString());
			
			//int serialno = memberdata.getMember_Id()+0000;
			String policy_No = Integer.toString(calendar.get(Calendar.YEAR))+Integer.toString(calendar.get(Calendar.MONTH)+1)
					+Integer.toString(calendar.get(Calendar.DATE))+String.format("%04d", policynumberfactor+1); 
			
			memberdata.setPolicy_No(policy_No);
			memberdata.setPolicy_Status("I");
			memberdata.setProcess_Date(new Date());
			
			
			String hql = "SELECT m.member_id FROM memberdata m WHERE m.Applicant_Full_name = :name and m.Date_of_Birth = :birth";
			Query q = session.createSQLQuery(hql);
			logger.debug("memberdata.getName() " + memberdata.getApplicant_Full_Name());
			logger.debug("memberdata.getDate_of_Birth() " + memberdata.getDate_of_Birth());
			q.setParameter("name", memberdata.getApplicant_Full_Name());
			q.setParameter("birth", memberdata.getDate_of_Birth());
			
			
			List validatelist = q.list();
			
			if(!validatelist.isEmpty()){
			logger.debug("validatelist " + validatelist.size());
			Iterator it = validatelist.iterator();
			while(it.hasNext())  
			{
				//logger.debug(it.next());
			 /* MemberData std = (MemberData)it.next();*/
				int memberid = (int) it.next();
				logger.debug(memberid);
			/*  logger.debug("std.getMember_Id() " + std.getMember_Id());
			  int memberId = std.getMember_Id();*/
			  Object obj4 = session.load(MemberData.class, new Integer(memberid));  	
			  MemberData s5 = (MemberData) obj4;
			  logger.debug(s5.getApplicant_Full_Name());
           //   s5.setPolicy_No(memberdata.getPolicy_No());
			  s5.setApplicant_Full_Name(memberdata.getApplicant_Full_Name());
			  s5.setDate_of_Birth(memberdata.getDate_of_Birth());
			  s5.setProcess_Date(new Date());
			  s5.setDoor_No(memberdata.getDoor_No());
			  s5.setCity(memberdata.getCity());
			  s5.setStreet(memberdata.getStreet());
			  s5.setPrem_Frequency(memberdata.getPrem_Frequency());
			  s5.setSSN(memberdata.getSSN());
			  s5.setCell_Phone_No(memberdata.getCell_Phone_No());
             // memberdata.setPolicy_No(s5.getPolicy_No());
             // memberdata.setProcess_Date(new Date());
              session.update(s5);
			   
			}
			logger.debug("validatelist " + validatelist.size());
			}
			else{
			session.save(memberdata);
			}
			transaction.commit();
			session.close();
	}	
	


	public void ValidationFailure(String failedfield, String remarks, MemberData memberdata){
		
		logger.debug("The validation is failure with the reason "+remarks);
		
		sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		org.hibernate.Transaction transaction = session.beginTransaction();
		
		failedmemberdata.setApplicant_Full_Name(memberdata.getApplicant_Full_Name());
		failedmemberdata.setDOB(memberdata.getDate_of_Birth().toString());
		failedmemberdata.setSSN(memberdata.getSSN());
		failedmemberdata.setError_Description(remarks);
		failedmemberdata.setField_in_Error(failedfield);
		session.saveOrUpdate(failedmemberdata);
		
		transaction.commit();
		session.close();
	}
	
	public int GenerateEnrolledExcel() throws IOException{
		sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		String hql = Constant.fetchingmemberdata;
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
	     
	      CellStyle cellStyle = workbook.createCellStyle();
	      CreationHelper createHelper = workbook.getCreationHelper();
	      cellStyle.setDataFormat(
	          createHelper.createDataFormat().getFormat("MM/dd/yyyy"));
	      
	      
	      CellStyle cellStyletext = workbook.createCellStyle();
	      cellStyletext.setDataFormat(
	          createHelper.createDataFormat().getFormat("Y"));
	      cell.setCellStyle(cellStyletext);
	      
	     
	      
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
	         cell.setCellStyle(cellStyle);
	         cell=row.createCell(11);
	         cell.setCellValue((String) Character.toString(m.getGender()));
	         cell=row.createCell(12);
	         cell.setCellValue(m.getSSN());
	         cell=row.createCell(13);
	         cell.setCellValue(m.getDate_Policy_Applied());
	         cell.setCellStyle(cellStyle);
	         cell=row.createCell(14);
	         cell.setCellValue((String) Character.toString(m.getStudent_Ind()));
	         cell=row.createCell(15);
	         cell.setCellValue((String) Character.toString(m.getHazardous_Occupation()));
	         cell=row.createCell(16);
	         cell.setCellValue((String) Character.toString(m.getHeart_Disease_present()));
	         cell=row.createCell(17);
	         cell.setCellValue((String) Character.toString(m.getInvolved_in_Aviation_Activities()));
	         cell=row.createCell(18);
	         cell.setCellValue((String) Character.toString(m.getDrinking_Smoking_Habits()));
	         cell=row.createCell(19);
	         cell.setCellValue(m.getPrem_Frequency());
	         cell=row.createCell(20);
	         cell.setCellValue(m.getAgent_Code());
	         cell=row.createCell(21);
	         cell.setCellValue(m.getCoverage_Amount());
	         cell=row.createCell(22);
	         cell.setCellValue((String) m.getPolicy_Status());
	         i++;
	      }
	      FileOutputStream out = new FileOutputStream(
	      new File("D:/Enrollment Process output folder/enrolledmemberdata.xlsx"));
	      workbook.write(out);
	      out.close();
	     logger.info(
	      "enrolledmemberdata.xlsx written successfully");
		}
		return memberdatalist.size();
		
		
	}
	
	public int GenerateFailureExcel() throws IOException{
		sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		String hql =  Constant.fetchingfailedmemberdata;
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
		      logger.info(
		      "failedmemberdata.xlsx written successfully");
		}
		return failedmemberdatalist.size();
	      
	}
	
	public void CallUpdateMasterMemberData(){
		UpdateMasterMemberData(filename,totalrecords,processedrecords,failurerecords);
	}
	
	public void UpdateMasterMemberData(String filename, int totalrecords, int processedrecord, int failurerecord){
		sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		org.hibernate.Transaction transaction = session.beginTransaction();
		
		mastermember.setFile_Name(filename);
		mastermember.setProcessed_Records(processedrecord);
		mastermember.setTotal_Records(totalrecords);
		mastermember.setFailed_Records(failurerecord);
		mastermember.setDate_Time(new Date());
		session.save(mastermember);
		
		transaction.commit();
		session.close();
		
		processedrecords = 0;
		failurerecords = 0;
		
		
	}
	
}

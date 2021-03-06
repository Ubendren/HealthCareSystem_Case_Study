package com.healthcaresystem.spring.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.healthcaresystem.spring.util.Constant;
import SpringException.SpringException;

import com.healthcaresystem.spring.model.MemberData;
import com.healthcaresystem.spring.model.PremiumMaster;
import com.healthcaresystem.spring.util.HibernateUtil;

public class CalculatePremiumDao {
	final static Logger logger = Logger.getLogger(com.healthcaresystem.spring.dao.CalculatePremiumDao.class);
	@Autowired
	MemberData memberdata;
	
	@Autowired
	PremiumMaster premiummaster;
	
	SessionFactory sessionFactory;

	
	int memberid;
	String policynumber;
	char gender;
	char hazardousoccupation;
	char heartdisease;
	char aviationactivities;
	char drinkingsmokinghabits;
	char studentind;
	int premiumfrequency;
	double coverageamount;
	Date dateofbirth;
	int age;
	double premiumamount;
	int weightage = 0;
	Date policyapplieddate;
	
	public void CalculatePremium() throws IOException, HibernateException, ParseException{
		sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, 1);
		
		Date currentdate = new Date();
		
		Query query = session.createQuery(Constant.calculatingpremiummaster);
		query.setParameter("currentdate", sdf.parse(sdf.format(new Date())));
		query.setParameter("nextdate", sdf.parse(sdf.format(calendar.getTime())));
		
		
		List<MemberData> memberdatalist = query.list();
		
		if(!memberdatalist.isEmpty()){
		
		for(MemberData memberdata:memberdatalist)
		{
			Transaction transaction=session.beginTransaction();
			memberid = memberdata.getMember_Id();
			policynumber = memberdata.getPolicy_No();
			gender = memberdata.getGender();
			hazardousoccupation = memberdata.getHazardous_Occupation();
			heartdisease = memberdata.getHeart_Disease_present();
			aviationactivities = memberdata.getInvolved_in_Aviation_Activities();
			drinkingsmokinghabits = memberdata.getDrinking_Smoking_Habits();
			studentind = memberdata.getStudent_Ind();
			premiumfrequency = memberdata.getPrem_Frequency();
			coverageamount = memberdata.getCoverage_Amount();
			dateofbirth = memberdata.getDate_of_Birth();
			policyapplieddate = memberdata.getDate_Policy_Applied();
			
			age = CalculateAge(dateofbirth);
			
			weightage = weightage + AgeWeightage(age);
			
			weightage = weightage + GenderWeightage(gender);
			
			weightage = weightage + OtherFactorsWeightage(hazardousoccupation, heartdisease, aviationactivities, drinkingsmokinghabits, studentind);
			
			weightage = weightage + CoverageAmountWeightage(coverageamount);
			
			premiumamount = PremiumCalculation(weightage, coverageamount);
			
			if(premiumamount == 0)
			{	logger.info("Rejected premium");
				memberdata.setPolicy_Status("H");
				session.update(memberdata);
			}
			else{
				logger.info("Not rejected premium");
				memberdata.setPolicy_Status("A");
				session.update(memberdata);
			}
			
			PopulatePremiumMasterTable(memberid,policynumber,premiumamount,policyapplieddate,premiumfrequency);
			
			GeneratePremiumMasterFile();
		transaction.commit();
		}
		session.close();
		}
		else
		{
			logger.info("No records found for calculating premium" );
		}
		
	}
	
	public int CalculateAge(Date dateofbirth)
	{
		
		      Calendar cal1 = new GregorianCalendar();
		      Calendar cal2 = new GregorianCalendar();
		      int age = 0;
		      int factor = 0; 
		      Date date1;
		      date1 = dateofbirth;

		      Date date2 = new Date();
			  cal1.setTime(date1);
			  cal2.setTime(date2);
			  if(cal2.get(Calendar.DAY_OF_YEAR) < cal1.get(Calendar.DAY_OF_YEAR)) {
				   factor = -1; 
			  }
			  age = cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR) + factor;
			  logger.debug("Your age is: "+age);
				return age;
	}
	
	public int AgeWeightage(int age){
		int ageweightage = 0;
		String agelimit = "";
		System.out.println(age);
		if(age>=1 && age<=25)
			agelimit = "1 <= x <= 25";
		else if(age>=26 && age>=35)
			agelimit = "26 <= x <= 35";
		else if(age>=36 && age>=45)
			agelimit = "36 <= x <= 45";
		else if(age>=46 && age>=55)
			agelimit = "46 <= x <= 55";
		else if(age>=56 && age>=65)
			agelimit = "56 <= x <= 65";
		else if(age>=66 && age>=75)
			agelimit = "66 <= x <= 75";
		else if(age>=76 && age>=85)
			agelimit = "76 <= x <= 85";
		else if(age>86)
			agelimit = "x>86";
		
		sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Query query = session.createSQLQuery(Constant.fetchingageweightage);
		query.setParameter("agelimit", agelimit);
		ageweightage = (int) query.list().get(0);
		logger.debug(ageweightage);
		
		session.close();
		
		return ageweightage;
	}
	
	public int GenderWeightage(char gender)
	{
		int genderweightage = 0;
		sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		Query query = session.createSQLQuery(Constant.fetchinggenderweightage);
		query.setParameter("gender", gender);
		genderweightage = (int) query.list().get(0);
		logger.debug(genderweightage);
		
		session.close();
		return genderweightage;
	}
	
	public int OtherFactorsWeightage(char hazardousoccupation, char heartdisease, char aviationactivities, char drinkingsmokinghabits, char studentind){
		int otherfactorsweightage = 0;
		
		sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		Query query = null;
		if(hazardousoccupation == 'Y'){
			query = session.createSQLQuery(Constant.fetchinghazardousweightage);
			otherfactorsweightage = otherfactorsweightage+(int)query.list().get(0);}
		if(heartdisease == 'Y'){
			query = session.createSQLQuery(Constant.fetchingheartdiseaseweightage);
			otherfactorsweightage = otherfactorsweightage+(int)query.list().get(0);}
		if(studentind == 'Y'){
			query = session.createSQLQuery(Constant.fetchingstudentweightage);
			otherfactorsweightage = otherfactorsweightage+(int)query.list().get(0);}
		if(drinkingsmokinghabits == 'Y'){
			query = session.createSQLQuery(Constant.fetchingdrinkingweightage);
			otherfactorsweightage = otherfactorsweightage+(int)query.list().get(0);}
		if(aviationactivities == 'Y'){
			query = session.createSQLQuery(Constant.fetchingaviationweightage);
			otherfactorsweightage = otherfactorsweightage+(int)query.list().get(0);}
		
		session.close();
		
		logger.debug(otherfactorsweightage);
		
		return otherfactorsweightage;
		
	}
	
	public int CoverageAmountWeightage(double coverageamount){
		int coverageweightage = 0;
		String coveragelimit = "";
		if(coverageamount < 50000)
			coveragelimit = "x <= $50,000";
		else if(coverageamount >= 50001 && coverageamount <= 75000)
			coveragelimit = "$50,001 <= x <= $75,000";
		else if(coverageamount >= 75001 && coverageamount <= 100000)
			coveragelimit = "$75,001 <= x <= $100,000";
		else if(coverageamount >= 100001 && coverageamount <= 200000)
			coveragelimit = "$100,001 <= x <= $200,000";
		else if(coverageamount >= 200001 && coverageamount <= 300000)
			coveragelimit = "$200,001 <= x <= $300,000";
		else if(coverageamount >= 300001 && coverageamount <= 400000)
			coveragelimit = "$300,001 <= x <= $400,000";
		else if(coverageamount >= 400001 && coverageamount <= 500000)
			coveragelimit = "$400,001 <= x <= $500,000";
		else if(coverageamount > 500001)
			coveragelimit = "x > $500,000";
		
		sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		Query query = session.createSQLQuery(Constant.fetchingcoverageweightage);
		query.setParameter("coveragelimit", coveragelimit);
		coverageweightage = (int) query.list().get(0);
		
		logger.debug(coverageweightage);
		
		session.close();
		return coverageweightage;
	}
	
	
	public double PremiumCalculation(int weightage, double coverageamount){
		double PremiumAmount = 0;
		int premiumfactor = 0;
		String weightagelimit = "";
		
		if(weightage>=5 && weightage<=10)
			weightagelimit = "5 <=  x <= 10";
		if(weightage>=11 && weightage<=15)
			weightagelimit = "11 <=  x <= 15";
		if(weightage>=16 && weightage<=20)
			weightagelimit = "16 <=  x <= 20";
		if(weightage>=21 && weightage<=25)
			weightagelimit = "21 <=  x <= 25";
		if(weightage>=26)
			weightagelimit = "x > 25";
		logger.debug(weightage);
		logger.debug(weightagelimit);
		sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		Query query = session.createSQLQuery(Constant.fetchingpremiumweightage);
		query.setParameter("weightagelimit", weightagelimit);
		
		
		if(query.list().get(0).toString().equals("Rejected"))
			premiumfactor = 0;
		else
		premiumfactor = Integer.parseInt(query.list().get(0).toString().substring(0, 2));
		
		PremiumAmount = (premiumfactor*coverageamount)/100;
		
		session.close();
		
		
		return PremiumAmount;
		
	}
	
	public void PopulatePremiumMasterTable(int memberid, String policynumber, double premiumamount, Date policyapplieddate, int premiumfrequency) {
		sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(policyapplieddate);
		
		Date premiumenddate = policyapplieddate;
		cal1.setTime(premiumenddate);
		
		int flag = 0;
		if(premiumfrequency == 1)
			flag=12;
		else if(premiumfrequency == 2)
			flag=6;
		else if(premiumfrequency == 4)
			flag=3;
		else if(premiumfrequency == 12)
			flag=1;
		
		
			
		cal1.add(Calendar.MONTH, flag);
		int i=1;
		for(int seqnum=1; seqnum<=premiumfrequency;seqnum++)
		{ 	
			String sql = "INSERT INTO premiummaster (Member_Id,Policy_Number,Sequence_Number,Premium_Start_Date,Premium_End_Date,Premium_Amount,Late_Fee,Premium_Paid_Date) "
					+ "VALUES(:memberid, :policnumber, :sequencenumber, :premiumstrtdate, :premiumendate, :premumamount, :latfee, :premiumpaidate)";
			Query query = session.createSQLQuery(sql);
			query.setParameter("memberid", memberid);
			query.setParameter("policnumber", policynumber);
			query.setParameter("sequencenumber", i);
			query.setParameter("premiumstrtdate", cal2.getTime());
			query.setParameter("premiumendate", cal1.getTime());
			query.setParameter("premiumpaidate", new Date());
			query.setParameter("premumamount", premiumamount);
			query.setParameter("latfee", 0);
			query.executeUpdate();
			i++;
			cal1.add(Calendar.MONTH, flag);
			cal2.add(Calendar.MONTH, flag);
			
		}
		
		transaction.commit();
		session.close();
	}
	
	public void GeneratePremiumMasterFile() throws IOException{
		sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		String sql = Constant.fetchingpremiummaster;
		Query query = session.createQuery(sql);
		List<PremiumMaster> premiummasterlist = query.list();
		logger.debug("The list of premium master list in generating excel method"+premiummasterlist.size());
		session.close();
		if(!premiummasterlist.isEmpty()){
		XSSFWorkbook workbook = new XSSFWorkbook(); 
	      XSSFSheet spreadsheet = workbook
	      .createSheet("Premium Master File");
	      XSSFRow row=spreadsheet.createRow(0);
	      XSSFCell cell;
	      cell=row.createCell(0);
	      cell.setCellValue("Member ID");
	      cell=row.createCell(1);
	      cell.setCellValue("Policy Number");
	      cell=row.createCell(2);
	      cell.setCellValue("Sequence Number");
	      cell=row.createCell(3);
	      cell.setCellValue("Premium Start Date");
	      cell=row.createCell(4);
	      cell.setCellValue("Premium End Date");
	      cell=row.createCell(5);
	      cell.setCellValue("Premium Amount");
	      cell=row.createCell(6);
	      cell.setCellValue("Late Fee");
	      cell=row.createCell(7);
	      cell.setCellValue("Premium Paid Date");
	      
	      
	      CellStyle cellStyle = workbook.createCellStyle();
	      CreationHelper createHelper = workbook.getCreationHelper();
	      cellStyle.setDataFormat(
	          createHelper.createDataFormat().getFormat("MM/dd/yyyy"));
	      
	      
	      
	      int i=1;
		     for(PremiumMaster m: premiummasterlist)
		      {
		         row=spreadsheet.createRow(i);
		         cell=row.createCell(0);
		         cell.setCellValue(m.getMember_Id());
		         logger.debug(m.getMember_Id());
		         cell=row.createCell(1);
		         cell.setCellValue(m.getPolicy_Number());
		         logger.debug(m.getPolicy_Number());
		         cell=row.createCell(2);
		         cell.setCellValue(m.getSequence_Number());
		         logger.debug(m.getSequence_Number());
		         cell=row.createCell(3);
		         cell.setCellValue(m.getPremium_Start_Date());
		         logger.debug(m.getPremium_Start_Date());
		         cell.setCellStyle(cellStyle);
		         cell=row.createCell(4);
		         cell.setCellValue(m.getPremium_End_Date());
		         logger.debug(m.getPremium_End_Date());
		         cell.setCellStyle(cellStyle);
		         cell=row.createCell(5);
		         cell.setCellValue(m.getPremium_Amount());
		         logger.debug(m.getPremium_Amount());
		         cell=row.createCell(6);
		         cell.setCellValue(m.getLate_Fee());
		         logger.debug(m.getLate_Fee());
		         cell=row.createCell(7);
		         cell.setCellValue(m.getPremium_Paid_Date());
		         logger.debug(m.getPremium_Paid_Date());
		         cell.setCellStyle(cellStyle);
		         i++;
		      }
		     FileOutputStream out = new FileOutputStream(
		   	      new File("D:/Enrollment Process output folder/premiummasterfile.xlsx"));
		   	      workbook.write(out);
		   	      out.close();
		   	     logger.info(
		   	      "premiummasterfile.xlsx written successfully");
		
	}
		
	}
}

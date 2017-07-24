package com.healthcaresystem.spring.dao;

	import com.healthcaresystem.spring.util.Constant;
	import com.healthcaresystem.spring.util.HibernateUtil;
	import java.io.File;
	import java.io.FileOutputStream;
	import java.io.IOException;
    import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

import com.healthcaresystem.spring.model.MemberData;
import com.healthcaresystem.spring.model.PremiumMaster;
import com.healthcaresystem.spring.model.PremiumProcess;
	import com.healthcaresystem.spring.model.PremiumRejectData;

	public class PremiumProcessorDao {
		
		PremiumRejectData rejectdata = new PremiumRejectData();
		PremiumProcess premiumprocess = new PremiumProcess();
		PremiumMaster premiummaster = new PremiumMaster();
		
		SessionFactory sessionFactory;
		
		Date policy_start_date;
		Date policy_end_date;
		double premium_amount;
		double late_fee;
	    int count;
		String filename;
		int totalrecords;
		int processedrecords;
		String policy_status;
		int failurerecords;
		int member_id;
		String policy_number;
		
		
		MemberData memberData =new MemberData();
		PremiumMaster premiumMaster=new PremiumMaster();
		
		
		public void ValidatePremiumData(String filenames, int totalrecord, PremiumProcess premiumprocess) throws IOException{
			
			
			
	
			count = 0;
			
			count = Validations(premiumprocess);
			
			if(count == 3)
				ValidationSuccess(premiumprocess);
			
			 processedrecords = GeneratePremiumExcel();
			 failurerecords = GenerateFailurePremium();
			filename = filenames;
			totalrecords = totalrecord;
			
			
		}
		public int Validations(PremiumProcess premiumProcess) throws IOException{
			
			member_id=premiumProcess.getMember_Id();
			policy_number=premiumProcess.getPolicy_Number();
			policy_start_date=premiumProcess.getPremium_Start_Date();
			policy_end_date=premiumProcess.getPremium_End_Date();
			premium_amount=premiumProcess.getPremium_Amount();
			late_fee=premiumProcess.getLate_Fee();
			policy_status=memberData.getPolicy_Status();
			count = 0;
			
			System.out.println(member_id+" "+policy_number+" "+policy_start_date+" "+policy_end_date
					+" "+premium_amount+" "+late_fee+" "+policy_status);
			
			sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			
			Query query = session.createQuery("FROM PremiumMaster WHERE Member_Id = :memberid AND Premium_Start_Date = :premiumstartdate");
			query.setParameter("memberid", member_id);
			query.setParameter("premiumstartdate", policy_start_date);
			
			
			List<PremiumMaster> premiummasterlist = query.list();
			
			for(PremiumMaster premiummaster : premiummasterlist){
			
				Query query1 = session.createSQLQuery("Select md.Policy_Status FROM memberdata md WHERE md.Member_Id = :memberid");
				query1.setParameter("memberid", premiummaster.getMember_Id());
				String policystatus = query1.list().get(0).toString();
				System.out.println(policystatus);
				System.out.println(policystatus.equals("A"));
			if(policystatus.equals("A"))
				count++;
			else
			{
				ValidationFailure("Policy_Status", "Policy Status is not active ", premiumProcess);
				return 0;
			}	
				
				System.out.println("premiummaster.getPremium_Amount()"+premiummaster.getPremium_Amount());
				System.out.println("premium_amount"+premium_amount);
			if(premiummaster.getPremium_Amount() == premium_amount)
				count++;
			
			else
			
				{ValidationFailure("Premium Amount", "Premium Amount is not match", premiumProcess);
				return 0;
				}
			System.out.println("premiummaster.getMember_Id() " + premiummaster.getMember_Id());
			
			System.out.println("member_id" + member_id);
            System.out.println("premiummaster.getPolicy_Number " + premiummaster.getPolicy_Number());
			
			System.out.println("policy_number" + policy_number);
			System.out.println("premiummaster.getPremium_Start_Date() " + premiummaster.getPremium_Start_Date());
			System.out.println("policy_start_date" + policy_start_date);
			System.out.println("premiummaster.getPremium_End_Date() " + premiummaster.getPremium_End_Date());
			System.out.println("policy_end_date" + policy_end_date);
            System.out.println("premiummaster.getLate_Fee() " + premiummaster.getLate_Fee());
			
			System.out.println("late_fee" + late_fee);
			
			System.out.println(premiummaster.getMember_Id()==member_id);
			System.out.println(premiummaster.getPolicy_Number().equals(policy_number));
			System.out.println(premiummaster.getPremium_Start_Date().equals(policy_start_date));
			System.out.println(premiummaster.getPremium_End_Date().equals(policy_end_date));
			
			
			
			
			
			
			if(premiummaster.getMember_Id()==member_id && premiummaster.getPolicy_Number().equals(policy_number) 
					&& premiummaster.getPremium_Start_Date().equals(policy_start_date) && premiummaster.getPremium_End_Date().equals(policy_end_date) )
				count++;
			else
				{ValidationFailure("Incorrect Mismatch", "Incorrect data", premiumProcess);
			return 0;
				}
			}
				//	if(member_id==memberdata.getMember_Id() && policy_number.equals(memberdata.getPolicy_No()) && policy_start_date.equals(memberdata.getS)
			
			return count;
		}
		
		

		
		public void ValidationSuccess(PremiumProcess premiumProcess){
			
				System.out.println("The validation is success");
				
				sessionFactory = HibernateUtil.getSessionFactory();
				Session session = sessionFactory.openSession();
				org.hibernate.Transaction transaction = session.beginTransaction();
				session.save(premiumProcess);
				transaction.commit();
				session.close();
		}	
		
		
		public void ValidationFailure(String failedfield, String remarks, PremiumProcess premiumProcess){
			
			System.out.println("The validation is failure with the reason "+remarks);
			
			sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			org.hibernate.Transaction transaction = session.beginTransaction();
			
			rejectdata.setMember_Id(premiumProcess.getMember_Id());
			rejectdata.setPolicy_Number(premiumProcess.getPolicy_Number());
			rejectdata.setPremium_Start_Date(premiumProcess.getPremium_Start_Date());
			rejectdata.setPremium_End_Date(premiumProcess.getPremium_End_Date());
			rejectdata.setPremium_Amount((premiumProcess.getPremium_Amount()));
			rejectdata.setLate_Fee(premiumProcess.getLate_Fee());
			rejectdata.setProcess_Date(new Date());
			rejectdata.setError_Description(remarks);
			/// process date
			
			
			session.save(rejectdata);
			
			transaction.commit();
			session.close();
		}
		
		
		
		public int GeneratePremiumExcel() throws IOException{
			sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			
			String hql = Constant.fetchingpremiumdata;
			Query query = session.createQuery(hql);
			List<PremiumProcess> premiumdatalist = query.list();
			session.close();
			if(!premiumdatalist.isEmpty()){
			XSSFWorkbook workbook = new XSSFWorkbook(); 
		      XSSFSheet spreadsheet = workbook
		      .createSheet("Enrolled Member Data");
		      XSSFRow row=spreadsheet.createRow(0);
		      XSSFCell cell;
		  
		      cell=row.createCell(0);
		      cell.setCellValue("Member Id");
		      cell=row.createCell(1);
		      cell.setCellValue("Policy Number");
		      cell=row.createCell(2);
		      cell.setCellValue("Premium Start Date");
		      cell=row.createCell(3);
		      cell.setCellValue("Premium End Date");
		      cell=row.createCell(4);
		      cell.setCellValue("Premium Amount");
		      cell=row.createCell(5);
		      cell.setCellValue("Late Fee");
		       
		      int i=1;
		     for(PremiumProcess p: premiumdatalist)
		      {
		         row=spreadsheet.createRow(i);
		       cell=row.createCell(0);
		         cell.setCellValue(p.getPolicy_Number());
		         cell=row.createCell(1);
		         cell.setCellValue(p.getPremium_Start_Date());
		         cell=row.createCell(2);
		         cell.setCellValue(p.getPremium_End_Date());
		         cell=row.createCell(3);
		         cell.setCellValue(p.getPremium_Amount());
		         cell=row.createCell(4);
		    
		         i++;
		      }
		      FileOutputStream out = new FileOutputStream(
		      new File("D:/Enrollment Process output folder/premiummemberdata.xlsx"));
		      workbook.write(out);
		      out.close();
		      System.out.println("premiummemberdata.xlsx written successfully");
			}
			return premiumdatalist.size();
			
			
		}
		
	public int GenerateFailurePremium() throws IOException{
			sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			
			String hql =  Constant.fetchingpremiumdetails;
			Query query = session.createQuery(hql);
			List<PremiumRejectData> failedrejectdatalist = query.list();
			
			session.close();
			
			if(!failedrejectdatalist.isEmpty())
			{
			XSSFWorkbook workbook = new XSSFWorkbook(); 
		      XSSFSheet spreadsheet = workbook
		      .createSheet("Failed Member Data");
		      XSSFRow row=spreadsheet.createRow(0);
		      XSSFCell cell;
		      cell=row.createCell(0);
		      cell.setCellValue("Member Id");
		      cell=row.createCell(1);
		      cell.setCellValue("Policy Number");
		      cell=row.createCell(2);
		      cell.setCellValue("Premium Start Date");
		      cell=row.createCell(3);
		      cell.setCellValue("Premium End Date");
		      cell=row.createCell(4);
		      cell.setCellValue("Premium Amount");
		      cell=row.createCell(5);
		      cell.setCellValue("Late Fee");
		      cell=row.createCell(6);
		      cell.setCellValue("Process Date");
		      
		      cell=row.createCell(7);
		      cell.setCellValue("Error Description");
		      
		    
		      
		      int i=1;
			     for(PremiumRejectData f: failedrejectdatalist)
			      {
			         row=spreadsheet.createRow(i);
			         cell=row.createCell(0);
			         cell.setCellValue(f.getMember_Id());
			         cell=row.createCell(1);
			         cell.setCellValue(f.getPolicy_Number());
			         cell=row.createCell(2);
			         cell.setCellValue(f.getPremium_Start_Date());
			         cell=row.createCell(3);
			         cell.setCellValue(f.getPremium_End_Date());
			         cell=row.createCell(4);
			         cell.setCellValue(f.getPremium_Amount());
			         cell=row.createCell(5);
			         cell.setCellValue(f.getLate_Fee());
			         cell=row.createCell(6);
			         cell.setCellValue(new Date());
			         cell=row.createCell(7);
			         cell.setCellValue("Policy Status Not Active");
			         i++;
			      }
			      FileOutputStream out = new FileOutputStream(
			      new File("D:/Enrollment Process output folder/failedpremiumdata.xlsx"));
			      workbook.write(out);
			      out.close();
			      System.out.println(
			      "failedpremiumdata.xlsx written successfully");
			}
			return failedrejectdatalist.size();
		      
		}
		
		/*public void CallUpdatePremiumData(){
			UpdatePremiumData(member_id,policy_number,policy_start_date,policy_end_date,premium_amount,late_fee);
		}
		
		public void UpdatePremiumData(int memberid,String policyno,Date startdate, Date enddate,double amount,double latefee)
		{
			sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			org.hibernate.Transaction transaction = session.beginTransaction();
			
			rejectdata.setMember_Id(memberid);
			rejectdata.setPolicy_Number(policyno);
			rejectdata.setPremium_Start_Date(startdate);
			rejectdata.setPremium_End_Date(enddate);
			rejectdata.setPremium_Amount(amount);
			rejectdata.setLate_Fee(latefee);
			rejectdata.setProcess_Date(new Date());
		//	rejectdata.setError_Description(error);
		
			session.save(rejectdata);
			
			transaction.commit();
			session.close();
			
		}
		*/
		
		
	}

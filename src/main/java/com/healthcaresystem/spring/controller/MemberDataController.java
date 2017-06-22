package com.healthcaresystem.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.healthcaresystem.spring.dao.MemberDataDao;
import com.healthcaresystem.spring.model.MemberData;

@Controller
public class MemberDataController {
@Autowired
MemberDataDao memberdatadao;
	
	@RequestMapping(value = "/processExcel", method = RequestMethod.POST)
	public void processExcel2007(Model model, @RequestParam("excelfile2007") MultipartFile excelfile) {	
		
		String filename = excelfile.getOriginalFilename();
		
		try {
			List<MemberData> lstMemberData = new ArrayList<>();
			int i = 1;
			// Creates a workbook object from the uploaded excelfile
			XSSFWorkbook workbook = new XSSFWorkbook(excelfile.getInputStream());
			// Creates a worksheet object representing the first sheet
			XSSFSheet worksheet = workbook.getSheetAt(0);
			// Reads the data in excel file until last row is encountered
			while (i <= worksheet.getLastRowNum()) {
				// Creates an object for the UserInfo Model
				MemberData memberdata = new MemberData();
				// Creates an object representing a single row in excel
				XSSFRow row = worksheet.getRow(i++);
				// Sets the Read data to the model class
				
				
				memberdata.setApplicant_Full_Name(row.getCell(0).getStringCellValue());
				memberdata.setDoor_No((int)row.getCell(1).getNumericCellValue());
				memberdata.setStreet(row.getCell(2).getStringCellValue());
				memberdata.setCity(row.getCell(3).getStringCellValue());
				memberdata.setState_code(row.getCell(4).getStringCellValue());
				memberdata.setCountry_Code(row.getCell(5).getStringCellValue());
				memberdata.setZip_Code(Integer.toString((int)row.getCell(6).getNumericCellValue()));
				memberdata.setCell_Phone_No(row.getCell(7).getStringCellValue());
				memberdata.setDate_of_Birth(row.getCell(8).getDateCellValue());
				memberdata.setGender((row.getCell(9).getStringCellValue()).charAt(0));
				memberdata.setSSN(row.getCell(10).getStringCellValue());
				memberdata.setDate_Policy_Applied(row.getCell(11).getDateCellValue());
				memberdata.setStudent_Ind((row.getCell(12).getStringCellValue()).charAt(0));
				memberdata.setHazardous_Occupation((row.getCell(13).getStringCellValue()).charAt(0));
				memberdata.setHeart_Disease_present((row.getCell(14).getStringCellValue()).charAt(0));
				memberdata.setInvolved_in_Aviation_Activities((row.getCell(15).getStringCellValue()).charAt(0));
				memberdata.setDrinking_Smoking_Habits((row.getCell(16).getStringCellValue()).charAt(0));
				memberdata.setPrem_Frequency((int)row.getCell(17).getNumericCellValue());
				memberdata.setAgent_Code(row.getCell(18).getStringCellValue());
				memberdata.setCoverage_Amount(row.getCell(19).getNumericCellValue());
				memberdata.setDeductible_Amount(row.getCell(20).getNumericCellValue());

				
				
				System.out.println(memberdata.getApplicant_Full_Name()+" "+memberdata.getDoor_No()+" "+memberdata.getStreet()
						+" "+memberdata.getCity()+" "+memberdata.getState_code()+" "+memberdata.getCountry_Code()+" "
						+memberdata.getZip_Code()+" "+memberdata.getCell_Phone_No()+" "+memberdata.getDate_of_Birth()+" "
						+memberdata.getGender()+" "+memberdata.getSSN()+" "+memberdata.getDate_Policy_Applied()+" "
						+memberdata.getStudent_Ind()+" "+memberdata.getHazardous_Occupation()+" "+memberdata.getHeart_Disease_present()
						+" "+memberdata.getInvolved_in_Aviation_Activities()+" "+memberdata.getDrinking_Smoking_Habits()+" "+memberdata.getPrem_Frequency()
						+" "+memberdata.getAgent_Code()+" "+memberdata.getCoverage_Amount()+" "+memberdata.getDeductible_Amount());
				

				
				// persist data into database in here
				lstMemberData.add(memberdata);
				memberdatadao.ValidateExcelData(filename,lstMemberData.size(),memberdata);
				
				memberdatadao.CallUpdateMasterMemberData();
			}			
			workbook.close();
			//model.addAttribute("lstMemberData", lstMemberData);
		} catch (Exception e) {
			e.printStackTrace();
		}
 
		
	}
 
}
	

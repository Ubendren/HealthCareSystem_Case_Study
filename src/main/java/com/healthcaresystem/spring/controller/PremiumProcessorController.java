package com.healthcaresystem.spring.controller;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.servlet.ModelAndView;

import com.healthcaresystem.spring.dao.PremiumProcessorDao;
import com.healthcaresystem.spring.model.PremiumMaster;
import com.healthcaresystem.spring.model.PremiumProcess;


@Controller
public class PremiumProcessorController {
@Autowired
PremiumProcessorDao premiumprocessordao;
		
		@RequestMapping(value = "/uploadPremium", method = RequestMethod.POST)
		public ModelAndView processExcel2007(Model model, @RequestParam("excelfile2007") MultipartFile excelfile) {	
			ModelAndView modelandview = null;
			String filename = excelfile.getOriginalFilename();
			
			try {
				List<PremiumProcess> lstPremiumData = new ArrayList<>();
				int i = 1;
				// Creates a workbook object from the uploaded excelfile
				XSSFWorkbook workbook = new XSSFWorkbook(excelfile.getInputStream());
				// Creates a worksheet object representing the first sheet
				XSSFSheet worksheet = workbook.getSheetAt(0);
				// Reads the data in excel file until last row is encountered
				while (i <= worksheet.getLastRowNum()) {
					// Creates an object for the UserInfo Model
					PremiumProcess premiumdata = new PremiumProcess();
					// Creates an object representing a single row in excel
					XSSFRow row = worksheet.getRow(i++);
					// Sets the Read data to the model class
					premiumdata.setMember_Id((int)row.getCell(0).getNumericCellValue());
					premiumdata.setPolicy_Number(Integer.toString((int)(row.getCell(1).getNumericCellValue())));
					premiumdata.setPremium_Start_Date((row.getCell(2).getDateCellValue()));
					premiumdata.setPremium_End_Date(row.getCell(3).getDateCellValue());
					premiumdata.setPremium_Amount((row.getCell(4).getNumericCellValue()));
					premiumdata.setLate_Fee((row.getCell(5).getNumericCellValue()));
							
					// persist data into database in here
					lstPremiumData.add(premiumdata);
				premiumprocessordao.ValidatePremiumData(filename,lstPremiumData.size(),premiumdata);
				
					
				}			
				workbook.close();
	//	premiumprocessordao.CallUpdatePremiumData();
				modelandview = new ModelAndView("uploadPremiumFile","successMsg","Upload successful");
				//model.addAttribute("lstMemberData", lstMemberData);
			} catch (Exception e) {
				e.printStackTrace();
				modelandview = new ModelAndView("uploadPremiumFile","successMsg","Uploading suspended due to exception!!!");
			}
			return modelandview;
	 
			
		}
	 
	}
		



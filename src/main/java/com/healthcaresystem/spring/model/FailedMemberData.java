package com.healthcaresystem.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="failedmemberdata")
public class FailedMemberData {
	
	@Id
	@Column(name="SSN")
	private String SSN ;
	
	@Column(name="DOB")
	private String DOB ;
	
	@Column(name="Applicant_Full_Name")
	private String Applicant_Full_Name ;
	
	@Column(name="Field_in_Error")
	private String Field_in_Error;
	
	@Column(name="Error_Description")
	private String  Error_Description;

	public String getSSN() {
		return SSN;
	}

	public void setSSN(String sSN) {
		SSN = sSN;
	}

	public String getDOB() {
		return DOB;
	}

	public void setDOB(String dOB) {
		DOB = dOB;
	}

	public String getApplicant_Full_Name() {
		return Applicant_Full_Name;
	}

	public void setApplicant_Full_Name(String applicant_Full_Name) {
		Applicant_Full_Name = applicant_Full_Name;
	}

	public String getField_in_Error() {
		return Field_in_Error;
	}

	public void setField_in_Error(String field_in_Error) {
		Field_in_Error = field_in_Error;
	}

	public String getError_Description() {
		return Error_Description;
	}

	public void setError_Description(String error_Description) {
		Error_Description = error_Description;
	}
	
	public FailedMemberData() {
		// TODO Auto-generated constructor stub
	}

}

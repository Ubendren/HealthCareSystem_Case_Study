package com.healthcaresystem.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="mastermember")
public class MasterMember {

	@Id
	@Column(name="File_Name")
	private String File_Name ;
	
	@Column(name="Total_Records")
	private int Total_Records ;
	
	@Column(name="Processed_Records")
	private int Processed_Records;
	
	@Column(name="Failed_Records")
	private int Failed_Records;
	
	@Column(name="Date_Time")
	private Date Date_Time ;

	public String getFile_Name() {
		return File_Name;
	}

	public void setFile_Name(String file_Name) {
		File_Name = file_Name;
	}

	public int getTotal_Records() {
		return Total_Records;
	}

	public void setTotal_Records(int total_Records) {
		Total_Records = total_Records;
	}

	public int getProcessed_Records() {
		return Processed_Records;
	}

	public void setProcessed_Records(int processed_Records) {
		Processed_Records = processed_Records;
	}

	public int getFailed_Records() {
		return Failed_Records;
	}

	public void setFailed_Records(int failed_Records) {
		Failed_Records = failed_Records;
	}

	public Date getDate_Time() {
		return Date_Time;
	}

	public void setDate_Time(Date date_Time) {
		Date_Time = date_Time;
	}
	
	public MasterMember() {
		// TODO Auto-generated constructor stub
	}
}

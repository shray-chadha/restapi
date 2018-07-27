package com.shray.automation.restapi.data;

public class NewUser {

	//This is the POJO class, The PLAIN OLD JAVA OBJECT
	
	//Declaring attributes
	String name;
	String job;
	
	
	//Creating a constructor to initiate the class object
	public NewUser (String name, String job) {
		this.name=name;
		this.job=job;
	}

	//Creating Getters and Setters 
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getJob() {
		return job;
	}


	public void setJob(String job) {
		this.job = job;
	}
	
}

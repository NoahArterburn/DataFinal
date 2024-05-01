package Final;
/***************************************************************
* Name : Noah Arterburn
* Author: Noah
* Created : 3/28/2024
* Course: CIS 152 - Data Structure
* Version: 1.0
* OS: Windows 11
* IDE: eclipse 2023,
* Copyright : This is my own original work 
* based on specifications issued by our instructor
* Description : This is the Person add or an HR staff entering information due to the amount of information given of personal order. This program is entering vaccination information. 
*            Input: gui and user dependent 
*            Output:gui and user dependent 
*            BigO: 0(1)
* Academic Honesty: I attest that this is my original work.
* I have not used unauthorized source code, either modified or
* unmodified. I have not given other fellow student(s) access
* to my program.
***************************************************************/

class Person {
	private String firstName;
	private String lastName;
	private long employeeID; 
	private boolean hasFluShot;
	private String batchNumber;
    private String vaccinationDate;
	
 // Constructor to initialize a person with first name, last name, and employee ID
 // Initially, person has not received a flu shot
	public Person(String firstName, String lastName, long employeeID) { // need to add batchNumber & vaccinationDate for val other than null at later date
		this.firstName = firstName;
		this.lastName = lastName;
		this.employeeID = employeeID;
		this.hasFluShot = false; 
		this.batchNumber = batchNumber; 
        this.vaccinationDate = vaccinationDate;
		
	}

	// Getter and setter methods
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public long getEmployeeID() {
		return employeeID;
	}

	public boolean hasFluShot() {
		return hasFluShot;
	}

	public void setFluShot(boolean hasFluShot) {
		this.hasFluShot = hasFluShot;
	}
	 public String getBatchNumber() {
	        return batchNumber;
	    }

	    public void setBatchNumber(String batchNumber) {
	        this.batchNumber = batchNumber;
	    }

	    public String getVaccinationDate() {
	        return vaccinationDate;
	    }

	    public void setVaccinationDate(String vaccinationDate) {
	        this.vaccinationDate = vaccinationDate;
	    }
}

package entities;

import java.io.Serializable;
/**
 * CREATE TABLE Instructor 
 * (	id varchar(50) NOT NULL,
 * 		First varchar(100),
 * 		Last varchar(100),
 * 		Department varchar(),
 * 		FOREIGN KEY(department) REFERENCES Department(code),
 * 		PRIMARY KEY (Id));
 */
public class Instructor implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String firstName;
	private String lastName;
	private String departmentId;
	
	public Instructor() {
	}

	public Instructor(String id, String firstName, String lastName, String dept) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.departmentId = dept;
	}
	public Instructor(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDepartment() {
		return departmentId;
	}
}

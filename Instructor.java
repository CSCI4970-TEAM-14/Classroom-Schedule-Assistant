package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CREATE TABLE Instructor 
 * (	Id INT NOT NULL,
 * 		First varchar(100),
 * 		Last varchar(100),
 * 		Department varchar(),
 * 		FOREIGN KEY(department) REFERENCES Department(code),
 * 		PRIMARY KEY (Id));
 */

@Entity
@Table(name = "Instructor")
public class Instructor implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "Id")
	private int Id;

	@Column(name = "First")
	private String firstName;

	@Column(name = "Last")
	private String lastName;

	@Column(name = "Department")
	private String departmentId;
	
	public Instructor() {
	}

	public Instructor(int Id, String firstName, String lastName, String dept) {
		this.Id = Id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.departmentId = dept;
	}

	public int getId() {
		return Id;
	}

	public void setId(int Id) {
		this.Id = Id;
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
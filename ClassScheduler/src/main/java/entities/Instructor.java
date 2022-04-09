package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CREATE TABLE Instructor 
 * (	id varchar(50) NOT NULL,
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
	@Column(name = "id")
	private String id;

	@Column(name = "First")
	private String firstName;

	@Column(name = "Last")
	private String lastName;

	@Column(name = "Department")
	private String departmentId;
	
	public Instructor() {
	}

	public Instructor(String id, String firstName, String lastName, String dept) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.departmentId = dept;
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

package models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CREATE TABLE Department 
 * (	code varchar() NOT NULL,
 * 		name varchar(),
 * 		building varchar(),
 * 		FOREIGN KEY(building) REFERENCES Building(ID),
 * 		PRIMARY KEY (code));
 */
@Entity
@Table(name = "Department")
public class Department implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "code")
	private String code;

	@Column(name = "name")
	private String name;
	
	@Column(name = "building")
	private String building;

	public Department() {
	}

	public Department(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setId(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getBuilding() {
		return building;
	}

}
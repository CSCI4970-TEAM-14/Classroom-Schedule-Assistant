package entities;

import java.io.Serializable;
/**
 * CREATE TABLE Department 
 * (	code varchar() NOT NULL,
 * 		name varchar(),
 * 		building varchar(),
 * 		FOREIGN KEY(building) REFERENCES Building(ID),
 * 		PRIMARY KEY (code));
 */
public class Department implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String code;

	private String name;
	
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
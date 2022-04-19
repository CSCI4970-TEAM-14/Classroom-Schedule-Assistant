package entities;

import java.io.Serializable;

/**
 * CREATE TABLE Course 
 * (	id varchar() NOT NULL,
 * 		title varchar(),
 * 		department varchar(),
 * 		term varchar(),
 * 		building varchar(10),
 * 		FOREIGN KEY(buildingId) REFERENCES Building(ID),
 * 		FOREIGN KEY(department) REFERENCES Department(code),
 * 		PRIMARY KEY (Id));
 */
public class Course implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String id;

	private String title;
	
	private String department;
	
	private String term;
	
	private String building;

	public Course() {
	}
	
	public Course(String id, String title) {
		this.id = id;
		this.title = title;
	}

	public Course(String id, String title, String department, String term, String building) {
		this.id = id;
		this.title = title;
		this.department = department;
		this.term = term;
		this.building = building;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDepartment() {
		return department;
	}

	public String getTerm() {
			return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}
	
	public String getBuilding() {
		return building;
	}
}
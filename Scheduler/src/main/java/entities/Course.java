package entities;

import java.io.Serializable;

/**
 * CREATE TABLE Course 
 * (	id varchar() NOT NULL,
 * 		sectionId int,
 * 		title varchar(),
 * 		term varchar(),
 * 		PRIMARY KEY (sectId));
 */
public class Course implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String id;
	
	private String sectId;

	private String title;
		
	private String term;


	public Course() {
	}
	
	public Course(String id, String title) {
		this.id = id;
		this.title = title;
	}

	public Course(String id, String title, String sectId, String term) {
		this.id = id;
		this.title = title;
		this.sectId = sectId;
		this.term = term;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getSection() {
		return sectId;
	}

	public void setSection(String sectId) {
		this.sectId = sectId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	

	public String getTerm() {
			return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}
	
}
package entities;

import java.io.Serializable;

/**
 * CREATE TABLE Section 
 * (	id int() AI NOT NULL,
 * 		sectionId varchar(),
 * 		courseId varchar(),
 *      method varchar(),
 *      instructor varchar(),
 *      term varchar(),
 *      enroll varchar(),
 * 		FOREIGN KEY(enroll) REFERENCES Classroom(seats),
 * 		FOREIGN KEY(courseId) REFERENCES Course(courseId),
 * 		PRIMARY KEY (Id));
 */
public class Section implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private  int id;
	
	private String sectionId;

	private String type;
	
	private String method;
	
	private String instructorId;
	
	private String term;
	
	private String enroll;
	
	private String meeting;
	
	private String room;
	
	private String courseId;

	public Section() {
	}

	public Section(String courseId,String sectionId,String method, String enroll,String instructorId,String term) {
		
		//this.id = id;
		this.sectionId = sectionId;
		//this.type = type;
		this.method = method;
		this.instructorId = instructorId;
		this.term = term;
		this.enroll = enroll;
		this.courseId = courseId;
	
	}

public Section(String courseId,String sectionId,String method) {
		
		this.sectionId = sectionId;
		this.method = method;
		this.courseId = courseId;
	
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getSection() {
		return sectionId;
	}

	public void setSection(String sectionId) {
		this.sectionId = sectionId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
		
	public String getInstructor() {
			return instructorId;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}
	
	public String getEnroll() {
		return enroll;
	}
	
	public String getMeet() {
		return meeting;
	}
	
	public String getRoom() {
		return room;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
		
	}

	public void setEnroll(String enroll) {
		this.enroll = enroll;
		
	}

	public void setRoom(String room) {
		this.room = room;
		
	}

	public void setInstructor(String instructor) {
		this.instructorId = instructor;
		
	}

	public void setMeet(String meeting) {
		this.meeting = meeting;
		
	}

}

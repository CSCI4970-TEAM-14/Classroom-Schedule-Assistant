package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CREATE TABLE Section 
 * (	id varchar() NOT NULL,
 * 		type varchar(),
 *      method varchar(),
 *      instructorId varchar(),
 *      term varchar(),
 *      enroll varchar(),
 * 		meeting varchar(),
 *      room varchar(),
 * 		courseId varchar(),
 * 		FOREIGN KEY(instructorId) REFERENCES Instructor(ID),
 * 		FOREIGN KEY(enroll) REFERENCES Enrollment(seats),
 * 		FOREIGN KEY(courseId) REFERENCES Course(ID),
 * 		FOREIGN KEY(room) REFERENCES Classroom(ID),
 * 		FOREIGN KEY(meeting) REFERENCES Schedule(day),
 * 		PRIMARY KEY (Id));
 */
@Entity
@Table(name = "Section")
public class Section implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "type")
	private String type;
	
	@Column(name = "method")
	private String method;
	
	@Column(name = "instructorId")
	private String instructorId;
	
	@Column(name = "term")
	private String term;
	
	@Column(name = "enroll")
	private String enroll;
	
	@Column(name = "meeting")
	private String meeting;
	
	@Column(name = "room")
	private String room;
	
	@Column(name = "courseId")
	private String courseId;

	public Section() {
	}

	public Section(String id, String courseId, String type, String method, String enroll, String room, String instructorId, String meeting,String term) {
		
		this.id = id;
		this.type = type;
		this.method = method;
		this.instructorId = instructorId;
		this.term = term;
		this.enroll = enroll;
		this.meeting = meeting;
		this.room = room;
		this.courseId = courseId;
	
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
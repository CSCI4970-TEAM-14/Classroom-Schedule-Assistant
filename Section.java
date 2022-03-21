package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CREATE TABLE Section 
 * (	Id INT NOT NULL,
 * 		type varchar(),
 *      method varchar(),
 *      instructorId varchar(),
 *      term varchar(),
 *      enroll INT(),
 * 		meeting varchar(),
 *      room INT(),
 * 		courseId INT,
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
	@Column(name = "Id")
	private int Id;

	@Column(name = "type")
	private String type;
	
	@Column(name = "method")
	private String method;
	
	@Column(name = "instructor")
	private String instructor;
	
	@Column(name = "term")
	private String term;
	
	@Column(name = "enroll")
	private int enroll;
	
	@Column(name = "meeting")
	private String meeting;
	
	@Column(name = "room")
	private int room;
	
	@Column(name = "courseId")
	private int courseId;

	public Section() {
	}

	public Section(int Id, String type, String method, String instructor, String term, int enroll, String meeting, int room, int courseId) {
		
		this.Id = Id;
		this.type = type;
		this.method = method;
		this.instructor = instructor;
		this.term = term;
		this.enroll = enroll;
		this.meeting = meeting;
		this.room = room;
		this.courseId = courseId;
	
	}

	public int getId() {
		return Id;
	}

	public void setId(int Id) {
		this.Id = Id;
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
			return instructor;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}
	
	public int getEnroll() {
		return enroll;
	}
	
	public String getMeet() {
		return meeting;
	}
	
	public int getRoom() {
		return room;
	}

	public int getCourseId() {
		return courseId;
	}

}

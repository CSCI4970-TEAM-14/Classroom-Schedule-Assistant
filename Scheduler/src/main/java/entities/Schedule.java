package entities;

import java.io.Serializable;

/**
 * CREATE TABLE Schedule 
 * (	
 * 		id int Auto increment NOT NULL,
 * 		room int NOT NULL,
 * 		courseId varchar(),
 * 		sectId int,
 * 		instructor varchar(),
 * 		day varchar(50) NOT NULL,
 * 		start varchar(),
 * 		end varchar(),
 * 		FOREIGN KEY(sectionId) REFERENCES Course(sectionId),
 * 		FOREIGN KEY(room) REFERENCES Classroom(Id),
 * 		FOREIGN KEY(courseId) REFERENCES Course(Id),
 *      PRIMARY KEY (id));
 */
public class Schedule implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String room;
	private String courseId;
	private String section;
	private String instructor;
	private String day;
	private String startTime;
	private String endTime;
	
	public Schedule() {
	}

	public Schedule(String room, String courseId, String section, String instructor, String day, String startTime, String endTime) {
		this.day = day;
		this.startTime = startTime;
		this.endTime = endTime;
		this.section = section;
	}

	public Schedule(String day, String startTime, String endTime) {
		this.day = day;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}
	
	public String getCourse() {
		return courseId;
	}

	public void setCourse(String courseId) {
		this.courseId = courseId;
	}
	
	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	
	public String getDay() {
		return day;
	}

	public void setDate(String day) {
		this.day = day;
	}

	public String getStartTime() {
		return startTime;
	}
	
	public String getEndTime() {
		return endTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	public void setSection(String section) {
		this.section = section;
	}
	
	public String setEndTime(String endTime) {
		return endTime;
	}
	
	public String getSection() {
		return section;
	}

	public int getId() { 
		return id;
	}
	public int setId(int id) {
		return id;
	}
}

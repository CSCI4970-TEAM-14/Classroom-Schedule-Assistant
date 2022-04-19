package entities;

import java.io.Serializable;

/**
 * CREATE TABLE Schedule 
 * (	day varchar(50) NOT NULL,
 * 		start varchar(),
 * 		end varchar(),
 * 		sectionId varchar(),
 * 		FOREIGN KEY(sectionId) REFERENCES Section(ID),
 *      //PRIMARY KEY (day));
 */
public class Schedule implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String day;
	private String startTime;
	private String endTime;
	private String sectionId;
	
	public Schedule() {
	}

	public Schedule(String day, String startTime, String endTime, String section) {
		this.day = day;
		this.startTime = startTime;
		this.endTime = endTime;
		this.sectionId = section;
	}

	public Schedule(String day, String startTime, String endTime) {
		this.day = day;
		this.startTime = startTime;
		this.endTime = endTime;
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
		this.sectionId = section;
	}
	
	public String setEndTime(String endTime) {
		return endTime;
	}
	
	public String getSection() {
		return sectionId;
	}
}

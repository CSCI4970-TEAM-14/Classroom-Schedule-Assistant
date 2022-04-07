package entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * CREATE TABLE Schedule 
 * (	day varchar(50) NOT NULL,
 * 		start varchar(),
 * 		end varchar(),
 * 		sectionId varchar(),
 * 		FOREIGN KEY(sectionId) REFERENCES Section(ID),
 *      //PRIMARY KEY (day));
 */
@Entity
@Table(name = "Schedule")
public class Schedule implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id // primary key
	@Column(name = "day")
	private String day;
	
	@Column(name = "startTime")
	private String startTime;
	
	@Column(name = "endTime")
	private String endTime;
	
	@Column(name = "sectionId")
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

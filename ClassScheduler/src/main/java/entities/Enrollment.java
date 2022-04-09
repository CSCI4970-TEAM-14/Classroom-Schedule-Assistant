package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CREATE TABLE Enrollment 
 * (	seat varchar() NOT NULL,
 * 		sectionId varchar(),
 * 		year varchar(),
 * 		term varchar(),
 * 		FOREIGN KEY(sectionId) REFERENCES Section(ID),
 * 		PRIMARY KEY (seats));
 */
@Entity
@Table(name = "Enrollment")
public class Enrollment implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "seats")
	private String seat;
	
	@Column(name = "sectionId")
	private String sectionId;

	@Column(name = "year")
	private String year;
	
	@Column(name = "term")
	private String term;

	public Enrollment() {
	} 

	public Enrollment(String seat, String section,String year, String term) {
		this.seat = seat;
		this.sectionId = section;
		this.year = year;
		this.term = term;
	}

	public String getSeats() {
		return seat;
	}

	public void setSeats(String seat) {
		this.seat = seat;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}
	
	public String getSection() {
		return sectionId;
	}
}

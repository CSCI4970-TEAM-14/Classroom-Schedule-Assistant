package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CREATE TABLE Enrollment 
 * (	Seats INT NOT NULL,
 * 		SectionId INT,
 * 		Year varchar(),
 * 		Term varchar(),
 * 		FOREIGN KEY(sectionId) REFERENCES Section(ID),
 * 		PRIMARY KEY (seats));
 */
@Entity
@Table(name = "Enrollment")
public class Enrollment implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "seats")
	private int seats;
	
	@Column(name = "sectionId")
	private int sectionId;

	@Column(name = "year")
	private String year;
	
	@Column(name = "term")
	private String term;

	public Enrollment() {
	} 

	public Enrollment(int seats, int section,String year, String term) {
		this.seats = seats;
		this.sectionId = section;
		this.year = year;
		this.term = term;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
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
	
	public int getSection() {
		return sectionId;
	}
}
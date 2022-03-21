package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CREATE TABLE Class 
 * (	id INT NOT NULL,
 * 		type varchar(100) NOT NULL,
 * 		building VARCHAR (10),
 *      seat INT NOT NULL,
 *      Computers INT,
 *      FOREIGN KEY(building) REFRENCES Building(ID),
 * 		PRIMARY KEY (Id));
 */
@Entity
@Table(name = "Classroom")
public class Classroom implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "Id")
	private int Id;

	@Column(name = "type")
	private String type;
	
	@Column(name = "building")
	private String building;
	
	@Column(name = "seat")
	private int seat;
	
	@Column(name = "computers")
	private int computers;

	public Classroom() {
	}

	public Classroom(int Id, String type, String building, int seat, int computers) {
		this.Id = Id;
		this.type = type;
		this.building = building;
		this.seat = seat;
		this.computers = computers;
	}
	
	public Classroom(int Id, String building, int seat) {
		this.Id = Id;
		this.building = building;
		this.seat = seat;
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
	
	public String getBuilding() {
		return building;
	}

	public int getSeat() {
		return seat;
	}

	public void setSeats(int seat) {
		this.seat = seat;
	}

	public int getComputers() {
		return computers;
	}

	public void setComputers(int computers) {
		this.computers = computers;
	}

}

package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CREATE TABLE Class 
 * (	id String varchar() NOT NULL,
 * 		type varchar(100) NOT NULL,
 * 		building VARCHAR (10),
 *      seat varchar() NOT NULL,
 *      Computers varchar(),
 *      FOREIGN KEY(building) REFRENCES Building(ID),
 * 		PRIMARY KEY (Id));
 */
@Entity
@Table(name = "Classroom")
public class Classroom implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "Id")
	private String Id;

	@Column(name = "type")
	private String type;
	
	@Column(name = "building")
	private String building;
	
	@Column(name = "seat")
	private String seat;
	
	@Column(name = "computers")
	private String computers;

	public Classroom() {
	}

	public Classroom(String Id, String type, String building, String seat, String computers) {
		this.Id = Id;
		this.type = type;
		this.building = building;
		this.seat = seat;
		this.computers = computers;
	}
	
	public Classroom(String Id, String building, String seat) {
		this.Id = Id;
		this.building = building;
		this.seat = seat;
	}
	
	public String getId() {
		return Id;
	}

	public void setId(String Id) {
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

	public String getSeat() {
		return seat;
	}

	public void setSeats(String seat) {
		this.seat = seat;
	}

	public String getComputers() {
		return computers;
	}

	public void setComputers(String computers) {
		this.computers = computers;
	}

}


package entities;

import java.io.Serializable;

/**
 * CREATE TABLE Class 
 * (	id String varchar() NOT NULL,
 * 		type varchar(100) NOT NULL,
 * 		building VARCHAR (10),
 *      seat varchar() NOT NULL,
 *      computers varchar(),
 *      FOREIGN KEY(building) REFRENCES Building(ID),
 * 		PRIMARY KEY (Id));
 */
public class Classroom implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String id;

	private String type;
	
	private String building;

	private String seat;
	
	
	private String computers;

	public Classroom() {
	}

	public Classroom(String id, String type, String building, String seat, String computers) {
		this.id = id;
		this.type = type;
		this.building = building;
		this.seat = seat;
		this.computers = computers;
	}
	
	public Classroom(String id, String building, String seat) {
		this.id = id;
		this.building = building;
		this.seat = seat;
	}
	public Classroom(String id) {
		this.id = id;
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

	public void setBuilding(String building) {
		this.building = building;
		
	}

}

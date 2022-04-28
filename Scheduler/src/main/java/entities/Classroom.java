package entities;

import java.io.Serializable;

/**
 * CREATE TABLE Class 
 * (	id int(),
 * 		room varchar(),
 * 		type varchar(50),
 *      seat int(20),
 *      computers int(),
 */
public class Classroom implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String id;

	private String type;

	private String seat;
	
	private String computers;

	public Classroom() {
	}

	public Classroom(String id, String type, String seat) {
		this.id = id;
		this.type = type;
		this.seat = seat;
	}
	
	public Classroom(String id, String type) {
		this.id = id;
		this.type = type;
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

package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CREATE TABLE Building 
 * (	code varchar(10) NOT NULL, 
 * 		Name varchar(100),
 * 		PRIMARY KEY (code));
 */
@Entity
@Table(name = "Building")
public class Building implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "cone")
	private String code;

	@Column(name = "Name")
	private String Name;

	public Building() {
	}

	public Building(String code, String Name) {
		this.code = code;
		this.Name = Name;
	}

	public String getCode() {
		return code;
	}

	public void setId(String code) {
		this.code = code;
	}

	public String getName() {
		return Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}

}

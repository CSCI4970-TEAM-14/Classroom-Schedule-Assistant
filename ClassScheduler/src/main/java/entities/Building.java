package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CREATE TABLE Building 
 * (	code varchar(10) NOT NULL, 
 * 		name varchar(100),
 * 		PRIMARY KEY (code));
 */
@Entity
@Table(name = "Building")
public class Building implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "code")
	private String code;

	@Column(name = "name")
	private String name;

	public Building() {
	}

	public Building(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setId(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

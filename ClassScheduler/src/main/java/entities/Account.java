package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
//import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CREATE TABLE Account 
 * (	id varchar(50) NOT NULL Auto_increment,
 * 		username varchar(45),
 * 		firstname varchar(),
 * 		lastname varchar(),
 * 		email varchar(),
 * 		password varchar(),
 * 		PRIMARY KEY (id));
 */

@Entity
@Table(name = "Account")
public class Account implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	 @Id
	 @GeneratedValue
	 @Column(name = "id")
	 private Integer id;
 
	@Column(name = "username")
	private String username;

	@Column(name = "firstname")
	private String firstname;

	@Column(name = "lastname")
	private String lastname;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;
		

	public Account() {

	}

	public Account(String userName, String firstName, String lastName,String email, String password) {
		this.username = userName;
		this.firstname = firstName;
		this.lastname = lastName;
		this.email = email;
		this.password = password;
	}
	
	public Account(String userName, String password) {
		this.username = userName;
		this.password = password;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String userName) {
		this.username = userName;
	}

	public String getFirstName() {
		return firstname;
	}

	public void setFirstName(String firstName) {
		this.firstname = firstName;
	}

	public String getLastName() {
		return lastname;
	}

	public void setLastName(String lastName) {
		this.lastname = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

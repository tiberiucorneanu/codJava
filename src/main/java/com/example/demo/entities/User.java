package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String firstName;
	private String lastName;
	private String email;
	private String grupaSerie;
	private String description; 
	private String password;

	public User() {

	}
	
	public User(Long id, String firstName2, String lastName2, String email2, String grupaSerie2, String description2,
			String password2) {
		setId(id);
		setFirstName(firstName);
		setLastName(lastName);
		setEmail(email);
		setGrupaSerie(grupaSerie);
		setDescription(description);
		setPassword(password);
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGrupaSerie() {
		return grupaSerie;
	}

	public void setGrupaSerie(String grupaSerie) {
		this.grupaSerie = grupaSerie;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + getId() + ", firstName=" + getFirstName() + ", lastName=" + getLastName() + ", email=" + getEmail()
				+ ", grupaSerie=" + getGrupaSerie() + ", description=" + getDescription() + ", password=" + getPassword() + "]";
	}
	
	
}
package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class VirtualMachine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	public String name;
	public String description;
	public Long userID;
	public String status;
	public String vm_box;
	public String memori;
	public String cpu;
	
	public VirtualMachine() {
	}
	
	public VirtualMachine(Long id, String name, String description, Long userID, String status, String vm_box,
			String memori, String cpu) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.userID = userID;
		this.status = status;
		this.vm_box = vm_box;
		this.memori = memori;
		this.cpu = cpu;
	}



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getUserID() {
		return userID;
	}
	public void setUserID(Long userID) {
		this.userID = userID;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getVm_box() {
		return vm_box;
	}
	public void setVm_box(String vm_box) {
		this.vm_box = vm_box;
	}
	public String getMemori() {
		return memori;
	}
	public void setMemori(String memori) {
		this.memori = memori;
	}
	public String getCPU() {
		return cpu;
	}
	public void setCPU(String cpu) {
		this.cpu = cpu;
	}

	@Override
	public String toString() {
		return "VirtualMachine [id=" + id + ", name=" + name + ", description=" + description + ", userID=" + userID
				+ ", status=" + status + ", vm_box=" + vm_box + ", memori=" + memori + ", cpu=" + cpu + "]";
	}
		
}

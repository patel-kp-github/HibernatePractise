package com.practise.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "address1")
public class Address {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "add_id")
//	@JoinColumn(name="id")
	private int id;

	@Column(name = "city")
	private String cityname;

//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "id")
//	private int stud_id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

//	public int getStud_id() {
//		return stud_id;
//	}

//	public void setStud_id(int stud_id) {
//		this.stud_id = stud_id;
//	}

	public Address() {

	}
	public Address(String city) {
		this.cityname =city;
	}
	public String toString() {
		return "id:"+id+" cityname:"+cityname;
	}
//id,city,stud_id
}

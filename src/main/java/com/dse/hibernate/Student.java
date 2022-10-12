package com.dse.hibernate;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@SequenceGenerator(initialValue=1, name="idgen", sequenceName="students_student_id_seq")
@Table(name="students")
public class Student implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer student_id;
	
	@Column(length=50)
	private String name;

	@Column(length=50)
	private String surname;

	@Column(length=50)
	private String email;

	@Column(length=8)
	private String phone;

	@Column(length=8)
	private String code;

	public Student(Integer student_id, String name, String surname, String email, String phone, String code) {
		this.student_id = student_id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.phone = phone;
		this.code = code;
	}

	public Student(String name, String surname, String email, String phone, String code) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.phone = phone;
		this.code = code;
	}

	public Student() {}

	public Integer getStudent_id() {
		return student_id;
	}
	public void setStudent_id(Integer student_id) {
		this.student_id = student_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "Id: " + student_id + ", name: " + name + ", surname: " + surname + ", email: " + email
				+ ", phone: " + phone + ", code" + code;
	}
}

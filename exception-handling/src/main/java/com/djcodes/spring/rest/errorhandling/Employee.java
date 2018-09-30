package com.djcodes.spring.rest.errorhandling;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  public int id;

  @Column(name = "first_name")
  public String fname;

  @Column(name = "last_name")
  public String lname;

  @Column(name = "dob")
  public Calendar dob;

  @Column(name = "salary")
  public Double salary;

  @Column(name = "gender")
  public String gender;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Calendar getDob() {
    return dob;
  }

  public void setDob(Calendar dob) {
    this.dob = dob;
  }

  public Double getSalary() {
    return salary;
  }

  public void setSalary(Double salary) {
    this.salary = salary;
  }

  public String getFname() {
    return fname;
  }

  public void setFname(String fname) {
    this.fname = fname;
  }

  public String getLname() {
    return lname;
  }

  public void setLname(String lname) {
    this.lname = lname;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  @Override
  public String toString() {
    return "Employee [id="
        + id
        + ", fname="
        + fname
        + ", lname="
        + lname
        + ", dob="
        + dob
        + ", salary="
        + salary
        + ", gender="
        + gender
        + "]";
  }
}

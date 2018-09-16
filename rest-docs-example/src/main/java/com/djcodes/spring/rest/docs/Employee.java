package com.djcodes.spring.rest.docs;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "employee")
public class Employee {

  @JsonView(Views.ViewOnly.class)
  @Id
  @Column(name = "id")
  public int id;

  @JsonView({Views.ViewOnly.class, Views.Updatable.class})
  @Size(min = 2, max = 30)
  @Column(name = "first_name")
  public String fname;

  @JsonView({Views.ViewOnly.class, Views.Updatable.class})
  @Size(min = 2, max = 30)
  @Column(name = "last_name")
  public String lname;

  @JsonView({Views.ViewOnly.class, Views.Updatable.class})
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  @Column(name = "dob")
  public Calendar dob;

  @JsonView({Views.ViewOnly.class, Views.Updatable.class})
  @Column(name = "salary")
  public Double salary;

  @JsonView({Views.ViewOnly.class, Views.Updatable.class})
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

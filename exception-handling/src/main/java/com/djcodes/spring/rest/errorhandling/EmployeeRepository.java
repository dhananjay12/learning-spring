package com.djcodes.spring.rest.errorhandling;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

  public Employee findByFname(String fname);

  public Iterable<Employee> findByLname(String lname);

  public List<Employee> findByFnameLike(String fname);

  public List<Employee> findByFnameContains(String fname);

  public List<Employee> findBySalaryEquals(double amount);

  public List<Employee> findBySalaryGreaterThan(double amount);

  public List<Employee> findByDobAfter(Calendar cal);

  public List<Employee> findByDobBetween(Calendar cal1, Calendar cal2);

  public List<Employee> findBySalaryGreaterThanOrDobAfter(double amount, Calendar cal);

  public List<Employee> findBySalaryGreaterThanAndDobAfter(double amount, Calendar cal);

  public List<Employee> findBySalaryGreaterThanOrderByFnameAsc(double amount);

  public List<Employee> findBySalaryGreaterThanOrderBySalaryDesc(double amount);

  public List<Employee> findTopByOrderBySalaryDesc();

  public List<Employee> findFirstByOrderByFnameAsc();

  public List<Employee> findTop2BySalaryGreaterThanOrderBySalaryDesc(double amount);
}

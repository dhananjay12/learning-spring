package com.mynotes.spring.repo;

import org.springframework.data.repository.CrudRepository;

import com.mynotes.spring.model.Employee;

public interface EmployeeRepo extends CrudRepository<Employee, Long> {

}

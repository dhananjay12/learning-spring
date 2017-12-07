package com.djcodes.spring.rest.errorhandling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

  @Autowired EmployeeRepository repository;

  @GetMapping(value = "/getAll")
  public ResponseEntity<?> getAll() {
    Iterable<Employee> list = repository.findAll();
    return ResponseEntity.ok(list);
  }

  @PostMapping(value = "/updateSalary/{id}")
  public ResponseEntity<?> updateSalary(@PathVariable int id, @RequestParam Double salary) {
    Employee aEmployee = repository.findOne(id);
    aEmployee.setSalary(salary);
    repository.save(aEmployee);
    return ResponseEntity.accepted().build();
  }

  @GetMapping(value = "/getsdById/{id}")
  public ResponseEntity<?> getById(@PathVariable int id) {
    Employee aEmployee = repository.findOne(id);
    return ResponseEntity.ok(aEmployee);
  }
}

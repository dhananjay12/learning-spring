package com.djcodes.spring.rest.errorhandling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    if (aEmployee == null) {
      VndErrors vndErrors = new VndErrors("" + id, String.format("employee %s not found", "" + id));
      HttpHeaders httpHeaders = new HttpHeaders();
      httpHeaders.setContentType(MediaType.parseMediaType("application/vnd.errors"));
      return new ResponseEntity(vndErrors, httpHeaders, HttpStatus.NOT_FOUND);
    }
    aEmployee.setSalary(salary);
    repository.save(aEmployee);
    return ResponseEntity.accepted().build();
  }

  @GetMapping(value = "/getsdById/{id}")
  public ResponseEntity<?> getById(@PathVariable int id) {
    Employee aEmployee = repository.findOne(id);
    if (aEmployee == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    return ResponseEntity.ok(aEmployee);
  }
}

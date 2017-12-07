package com.djcodes.spring.rest.errorhandling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
      throw new EmployeeNotFoundException(id);
    }
    aEmployee.setSalary(salary);
    repository.save(aEmployee);
    return ResponseEntity.accepted().build();
  }

  @GetMapping(value = "/getById/{id}")
  public ResponseEntity<?> getById(@PathVariable int id) {
    Employee aEmployee = repository.findOne(id);
    if (aEmployee == null) {
      throw new EmployeeNotFoundException(id);
    }
    return ResponseEntity.ok(aEmployee);
  }
}

@ControllerAdvice(annotations = RestController.class)
class EmployeeControllerErrorControllerAdvice {

  @ExceptionHandler(EmployeeNotFoundException.class)
  public ResponseEntity<VndErrors> employeeNotFoundException(EmployeeNotFoundException e) {
    return errorResponseEntity(e, HttpStatus.NOT_FOUND, e.getEmployeeId() + "");
  }

  protected <E extends Exception> ResponseEntity<VndErrors> errorResponseEntity(
      E e, HttpStatus httpstatus, String logref) {
    MediaType mediaType = MediaType.parseMediaType("application/vnd.errors");
    String msg =
        StringUtils.hasText(e.getMessage()) ? e.getMessage() : e.getClass().getSimpleName();
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(mediaType);
    return new ResponseEntity<VndErrors>(new VndErrors(logref, msg), httpHeaders, httpstatus);
  }
}

class EmployeeNotFoundException extends RuntimeException {
  private int employeeId;

  public EmployeeNotFoundException(int id) {
    super("Employee#" + id + " not found");
    this.employeeId = id;
  }

  public int getEmployeeId() {
    return employeeId;
  }
}

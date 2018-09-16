package com.djcodes.spring.rest.errorhandling;

import org.h2.api.ErrorCode;
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

import com.djcodes.spring.rest.errorhandling.exceptions.EmployeeNotFoundException;
import com.djcodes.spring.rest.errorhandling.exceptions.ErrorCodes;
import com.djcodes.spring.rest.errorhandling.exceptions.ValidationException;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeRepository repository;

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

		if (id <= 0) {
			throw new ValidationException(ErrorCodes.VALIDATION_PARSE_ERROR);
		}
		Employee aEmployee = repository.findOne(id);
		if (aEmployee == null) {
			throw new EmployeeNotFoundException(id);
		}
		return ResponseEntity.ok(aEmployee);
	}
}

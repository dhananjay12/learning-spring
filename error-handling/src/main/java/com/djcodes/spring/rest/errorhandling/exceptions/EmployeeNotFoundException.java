package com.djcodes.spring.rest.errorhandling.exceptions;

public class EmployeeNotFoundException extends RuntimeException {
	private int employeeId;

	public EmployeeNotFoundException(int id) {
		super("Employee#" + id + " not found");
		this.employeeId = id;
	}

	public int getEmployeeId() {
		return employeeId;
	}
}

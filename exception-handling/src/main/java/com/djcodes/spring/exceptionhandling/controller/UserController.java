package com.djcodes.spring.exceptionhandling.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.djcodes.spring.exceptionhandling.exceptions.UserNotFoundException;
import com.djcodes.spring.exceptionhandling.exceptions.ValidationException;
import com.djcodes.spring.exceptionhandling.model.User;

@RestController
public class UserController {

	private static List<User> userList = new ArrayList<>();
	static {
		userList.add(new User(1, "John", 24));
		userList.add(new User(2, "Jane", 22));
		userList.add(new User(3, "Max", 27));
	}

	@GetMapping(value = "/user/{id}")
	public ResponseEntity<?> getUser(@PathVariable int id) {
		if (id < 0) {
			throw new ValidationException("Id cannot be less than 0");
		}
		User user = findUser(id);

		return ResponseEntity.ok(user);
	}

	@PatchMapping(value = "/user/{id}")
	public ResponseEntity<?> updateAge(@PathVariable int id, @RequestParam int age) {
		if (id < 0) {
			throw new ValidationException("Id cannot be less than 0");
		}
		if (age < 20 || age > 60) {
			throw new ValidationException("Age must be between 20 to 60");
		}
		User user = findUser(id);
		user.setAge(age);

		return ResponseEntity.accepted().body(user);
	}

	private User findUser(int id) {
		return userList.stream().filter(user -> user.getId().equals(id)).findFirst()
				.orElseThrow(() -> new UserNotFoundException());
	}

}

package com.exam.controller;

import java.util.HashSet;
import java.util.Set;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	//Creating User 
	@PostMapping("/")
	public User createUser(@RequestBody User user) throws Exception {
		
		user.setProfile("default.png");
		//Encoding password with BCrypt Password
		
		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
		Set<UserRole> roles=new HashSet<>();
		Role role = new Role();
		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);
		role.setRoleId(45L);
		role.setRoleName("NORMAL");
		roles.add(userRole);
		
		return this.userService.createUser(user, roles);
		
	}
	@GetMapping("/{userName}")
	public User getUser(@PathVariable("userName") String userName) {
		
		return this.userService.getUser(userName);
	}
	
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable("userId") Long userId) {
		this.userService.deleteUser(userId);
		}
	
	
}

package com.learning.coursefun.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserLoginDto{
	@Email(message="invalid email")
	@NotEmpty
    String email;
    @NotNull(message="kindly enter password")
    @NotEmpty
    String pass;
    @NotNull(message="kindly select role")
    @NotEmpty
    String role;

	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public UserLoginDto(String email, String pass, String role) {
		super();
		
		this.email = email;
		this.pass = pass;
		this.role = role;
	}
	public UserLoginDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "UserDto [ email=" + email + ", pass=" + pass + ", role=" + role + "]";
	}
    
    

}

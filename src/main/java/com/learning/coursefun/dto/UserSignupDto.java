package com.learning.coursefun.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserSignupDto {
	    @NotNull(message = "name cannot be null")
	    @NotEmpty
	    String name;
	    @Email(message="invalid email")
	    @NotEmpty
	    String email;
	    @Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$",message="enter valid password")
	    @NotEmpty
	    String pass;
	    @NotNull(message="kindly select role")
	    @NotEmpty
	    String role;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
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
	
		public UserSignupDto(@NotNull(message = "name cannot be null") String name,
				@Email(message = "invalid email") String email,
				@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$", message = "enter valid password") String pass,
				@NotNull(message = "kindly select role") String role) {
			super();
			this.name = name;
			this.email = email;
			this.pass = pass;
			this.role = role;
		}
		public UserSignupDto() {
			super();
			// TODO Auto-generated constructor stub
		}
	    
	    

}

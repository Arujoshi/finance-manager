package com.finance.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long userId;
	
	@Column(name="Name")
	@NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must not exceed 100 characters")
	private String name;
	
	@Column(name="EmailId")
	@NotBlank(message = "Email ID is required")
    @Email(message = "Email ID must be a valid email address")
	private String email;
	
	@Column(name="ContactNo")
	@NotBlank(message = "Phone number is required")
    @Pattern(regexp = "\\d{10}", message = "Phone number must be a valid 10-digit number")
	private String phone;
	
	@Column(name="Roles")
	@NotBlank(message = "Roles are required")
    @Size(max = 50, message = "Roles must not exceed 50 characters")
	private String roles;
	
	@Column(name="Password")
	@NotBlank(message = "Password is required")
	@Size(min = 10, message = "Password must have 10 characters")
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).+$", message = "Password must contain at least one uppercase letter and one digit")
	private String password;
	
	@Column
	private Integer failedLoginAttempts=0;
	
	@Column
	private Boolean locked;

}

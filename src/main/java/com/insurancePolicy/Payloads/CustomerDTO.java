package com.insurancePolicy.Payloads;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

	private int customerId;
	
	@NotBlank
	@Size(min = 4,message="Minimum 4 Character should be present in UserName")
	private String customerName;
	@Email(message ="Your Email is Incorrect Please check it Once Again!!!")
	private String customerEmail;
	@NotEmpty
	@Size(min = 3, max = 50,message="Your Adrress should in Limit of min 3 to max 50")
	private String customerAddress;
	private PolicyDTO policy;
}

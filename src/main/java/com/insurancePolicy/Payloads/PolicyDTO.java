package com.insurancePolicy.Payloads;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PolicyDTO {

	private int policyId;
	@NotNull
	private double amount;
	@NotEmpty
	@Size(min=3,max=20,message="Your policy type should be between min 3 to max 20")
	private String type;
	private boolean status;
	@NotNull
	private Date startDate;
	@NotNull
	private Date endDate;
}

package com.insurancePolicy.Payloads;




import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClaimDTO {

	private int claimId;
	private CustomerDTO customer;
	private PolicyDTO policy;
	@NotNull
	private double claimAmount;
}

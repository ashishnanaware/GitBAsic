package com.insurancePolicy.Service;


import java.util.List;

import com.insurancePolicy.Payloads.ClaimDTO;

public interface ClaimService {
	//create
	ClaimDTO createClaim(ClaimDTO claimDto,Integer customerId,Integer policyId);
	//get claim by id
	ClaimDTO getClaimById(Integer claimId);
	//get all claim by category
	List<ClaimDTO>getClaimByCustomer(Integer customerId);
	//get all claim by policy
	List<ClaimDTO>geClaimByPolicy(Integer policyId);
	//search claim
	List<ClaimDTO>searchClaim(Integer claimId);

}

package com.insurancePolicy.Service;

import java.util.List;

import com.insurancePolicy.Payloads.PolicyDTO;



public interface PolicyService {
	//create
	PolicyDTO createPolicy(PolicyDTO policy);
	//update
	PolicyDTO updatePolicy(PolicyDTO policy,Integer policyId);
	//get by id
	PolicyDTO getPolicyById(Integer policyId);
	//get all
	List<PolicyDTO>getAllPolicy();
	//delete policy
	void deletePolicy(Integer policyId);

}

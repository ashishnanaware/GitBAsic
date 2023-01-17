package com.insurancePolicy.Service;

import java.util.List;

import com.insurancePolicy.Payloads.ClaimDTO;
import com.insurancePolicy.Payloads.CustomerDTO;

public interface CustomerService {
	//create
	CustomerDTO createCustomer(CustomerDTO customerDTO,Integer policyId);
	
	//update
	CustomerDTO updateCustomer(CustomerDTO customerDto,Integer customerId);  
	
	//get customer by id
	CustomerDTO getCustomer(Integer customerId);
	
	//get all customer
	List<CustomerDTO> getAllCustomer();
	
	//delete
	void deleteCustomer(Integer customerId);
	
	//get customer by policy id
	List<CustomerDTO>geCustomerByPolicy(Integer policyId);

}

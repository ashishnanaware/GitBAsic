package com.insurancePolicy.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurancePolicy.Entity.Claim;
import com.insurancePolicy.Entity.Customer;
import com.insurancePolicy.Entity.Policy;
import com.insurancePolicy.Exception.ResourceNotFoundException;
import com.insurancePolicy.Payloads.ClaimDTO;
import com.insurancePolicy.Payloads.CustomerDTO;
import com.insurancePolicy.Repository.CustomerRepo;
import com.insurancePolicy.Repository.PolicyRepo;
import com.insurancePolicy.Service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepo repo;
	
	@Autowired
	private PolicyRepo policyRepo;
	
	@Autowired
	private ModelMapper modelMapper;
    
	//Create
	@Override
	public CustomerDTO createCustomer(CustomerDTO customerDto,Integer policyId) {
		Policy policy = this.policyRepo.findById(policyId).orElseThrow(()->new ResourceNotFoundException("Policy", "Policy Id", policyId));
		Customer cust = this.modelMapper.map(customerDto, Customer.class);
		cust.setPolicy(policy);
		Customer addCust = this.repo.save(cust);
		return this.modelMapper.map(addCust,CustomerDTO.class);
	}
    //Update
	@Override
	public CustomerDTO updateCustomer(CustomerDTO customerDto, Integer customerId) {
	    Customer cust = this.repo.findById(customerId).orElseThrow(()->new ResourceNotFoundException("Customer", "customerId", customerId));
		cust.setCustomerName(customerDto.getCustomerName());
		cust.setCustomerEmail(customerDto.getCustomerEmail());
		cust.setCustomerAddress(customerDto.getCustomerAddress());
		Customer updatedCust = this.repo.save(cust);
	    return this.modelMapper.map(updatedCust, CustomerDTO.class);
	}
    //delete
	@Override
	public void deleteCustomer(Integer customerId) {
		 Customer cust = this.repo.findById(customerId).orElseThrow(()->new ResourceNotFoundException("Customer", "customerId", customerId));
         this.repo.delete(cust);
	}
    //get by id
	@Override
	public CustomerDTO getCustomer(Integer customerId) {
		 Customer cust = this.repo.findById(customerId).orElseThrow(()->new ResourceNotFoundException("Customer", "customerId", customerId));
		return this.modelMapper.map(cust, CustomerDTO.class);
	}
    //get all customer
	@Override
	public List<CustomerDTO> getAllCustomer() {
		 List<Customer> customer = this.repo.findAll();
		 List<CustomerDTO> custDtos = customer.stream().map((cust)-> this.modelMapper.map(cust, CustomerDTO.class)).collect(Collectors.toList());
		 return custDtos;
	}
	//get customer by policy id
	@Override
	public List<CustomerDTO>geCustomerByPolicy(Integer policyId)
	{
		Policy policy = this.policyRepo.findById(policyId).orElseThrow(()->new ResourceNotFoundException("Policy", "PolicyId", policyId));
		List<Customer>custs = this.repo.getCustomerByPolicy(policy);
		List<CustomerDTO>custDtos = custs.stream().map((cust)->this.modelMapper.map(cust, CustomerDTO.class)).collect(Collectors.toList());
		return custDtos;
	}

}

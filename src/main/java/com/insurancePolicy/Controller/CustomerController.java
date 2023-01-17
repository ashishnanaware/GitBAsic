package com.insurancePolicy.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insurancePolicy.Payloads.ApiResponse;
import com.insurancePolicy.Payloads.CustomerDTO;
import com.insurancePolicy.Service.CustomerService;

import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	//create custome
	@PostMapping("/policy/{policyId}")
	public ResponseEntity<CustomerDTO>createCustomer(@Valid @RequestBody CustomerDTO custDto,@PathVariable Integer policyId)
	{   
		CustomerDTO createCustomer = this.customerService.createCustomer(custDto,policyId);
		return new ResponseEntity<CustomerDTO>(createCustomer,HttpStatus.CREATED);
	}
	//update customer
	@PutMapping("/{customerId}")
	public ResponseEntity<CustomerDTO>updateCustomer(@Valid @RequestBody CustomerDTO custDto,@PathVariable Integer customerId)
	{
		CustomerDTO updateCustomer = this.customerService.updateCustomer(custDto,customerId);
		return new ResponseEntity<CustomerDTO>(updateCustomer,HttpStatus.OK);
	}
	//delete customer
	@DeleteMapping("/{customerId}")
	public ResponseEntity<ApiResponse>deleteCustomer(@PathVariable Integer customerId)
	{
	    this.customerService.deleteCustomer(customerId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Customer is Deleted Successfully", true),HttpStatus.OK);
	}
	//get customer by id
	@GetMapping("/{customerId}")
	public ResponseEntity<CustomerDTO>getCustomer(@PathVariable Integer customerId)
	{
		CustomerDTO getCust = this.customerService.getCustomer(customerId);
		return new ResponseEntity<CustomerDTO>(getCust,HttpStatus.OK);
	}
	//get all customer
	@GetMapping("/")
	public ResponseEntity<List<CustomerDTO>>getAllCustomer()
	{
		List<CustomerDTO>getCust = this.customerService.getAllCustomer();
		return ResponseEntity.ok(getCust);
	}
	//get customer by policy id
	@GetMapping("/policy/{policyId}")
	public ResponseEntity<List<CustomerDTO>>getCustomerByPolicy(@PathVariable Integer policyId)
	{
		List<CustomerDTO> custs = this.customerService.geCustomerByPolicy(policyId);
		return new ResponseEntity<List<CustomerDTO>>(custs,HttpStatus.OK);
	}


}

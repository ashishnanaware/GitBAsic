package com.insurancePolicy.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insurancePolicy.Entity.Customer;
import com.insurancePolicy.Entity.Policy;

public interface CustomerRepo  extends JpaRepository<Customer, Integer>{

	List<Customer> getCustomerByPolicy(Policy policy);

}

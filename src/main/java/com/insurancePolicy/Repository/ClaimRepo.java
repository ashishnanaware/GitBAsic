package com.insurancePolicy.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.insurancePolicy.Entity.Claim;
import com.insurancePolicy.Entity.Customer;
import com.insurancePolicy.Entity.Policy;

public interface ClaimRepo extends JpaRepository<Claim, Integer> {
	List<Claim>findByPolicy(Policy policy);
	List<Claim>findByCustomer(Customer customer);
	List<Claim>findByClaimId(Integer claimId);
}

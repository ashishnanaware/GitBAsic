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
import com.insurancePolicy.Repository.ClaimRepo;
import com.insurancePolicy.Repository.CustomerRepo;
import com.insurancePolicy.Repository.PolicyRepo;
import com.insurancePolicy.Service.ClaimService;

@Service
public class ClaimServiceImpl implements ClaimService {
	
	@Autowired
	private ClaimRepo repo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PolicyRepo policyRepo;
	@Autowired
	private CustomerRepo custRepo;

	@Override
	public ClaimDTO createClaim(ClaimDTO claimDto, Integer customerId, Integer policyId) {
		Policy policy = this.policyRepo.findById(policyId).orElseThrow(()->new ResourceNotFoundException("Policy", "Policy Id", policyId));
		Customer customer = this.custRepo.findById(customerId).orElseThrow(()->new ResourceNotFoundException("Customer", "Customer Id", customerId));
		Claim claim = this.modelMapper.map(claimDto, Claim.class);
		claim.setPolicy(policy);
		claim.setCustomer(customer);
		Claim  newClaim = this.repo.save(claim);
		return this.modelMapper.map(newClaim,ClaimDTO.class);
	}

	@Override
	public ClaimDTO getClaimById(Integer claimId) {
		Claim  claim = this.repo.findById(claimId).orElseThrow(()->new ResourceNotFoundException("Claim", "ClaimId", claimId));
		return this.modelMapper.map(claim, ClaimDTO.class);  
	}

	@Override
	public List<ClaimDTO> getClaimByCustomer(Integer customerId) {
		Customer cust = this.custRepo.findById(customerId).orElseThrow(()->new ResourceNotFoundException("Customer", "customerId", customerId));
		List<Claim>claims = this.repo.findByCustomer(cust);
		
		List<ClaimDTO>claimDtos = claims.stream().map((claim)->this.modelMapper.map(claim, ClaimDTO.class)).collect(Collectors.toList());
		return claimDtos;
	}

	@Override
	public List<ClaimDTO> geClaimByPolicy(Integer policyId) {
		Policy policy = this.policyRepo.findById(policyId).orElseThrow(()->new ResourceNotFoundException("Policy", "PolicyId", policyId));
		List<Claim>claims = this.repo.findByPolicy(policy);
		List<ClaimDTO>claimDtos = claims.stream().map((claim)->this.modelMapper.map(claim, ClaimDTO.class)).collect(Collectors.toList());
		return claimDtos;
	}

	@Override
	public List<ClaimDTO> searchClaim(Integer claimId) {
		List<Claim>claims =this.repo.findByClaimId(claimId);
		List<ClaimDTO>claimDtos = claims.stream().map((claim)->this.modelMapper.map(claim, ClaimDTO.class)).collect(Collectors.toList());
		return claimDtos;
	}

}

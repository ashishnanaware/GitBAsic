package com.insurancePolicy.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insurancePolicy.Payloads.ClaimDTO;
import com.insurancePolicy.Service.ClaimService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/")
public class ClaimController {
	
	@Autowired
	private ClaimService claimService;
	
	    //create claim
		@PostMapping("/customer/{customerId}/policy/{policyId}/claims")
		public ResponseEntity<ClaimDTO>createClaim(@Valid @RequestBody ClaimDTO claimDto,@PathVariable Integer customerId, @PathVariable Integer policyId)
		{
			ClaimDTO createClaim = this.claimService.createClaim(claimDto, customerId, policyId);
			return new ResponseEntity<ClaimDTO>(createClaim,HttpStatus.CREATED);
		}
		//get claim by customer
		@GetMapping("/customer/{customerId}/claims")
		public ResponseEntity<List<ClaimDTO>>getClaimByCustomer(@PathVariable Integer customerId)
		{
			List<ClaimDTO> claims = this.claimService.getClaimByCustomer(customerId);
			return new ResponseEntity<List<ClaimDTO>>(claims,HttpStatus.OK);
		}
		//get claim by policy
		@GetMapping("/policy/{policyId}/claims")
		public ResponseEntity<List<ClaimDTO>>getClaimByPolicy(@PathVariable Integer policyId)
		{
			List<ClaimDTO> claims = this.claimService.geClaimByPolicy(policyId);
			return new ResponseEntity<List<ClaimDTO>>(claims,HttpStatus.OK);
		}
		//search claim
	    @GetMapping("/claims/search/{claimId}")
		public ResponseEntity<List<ClaimDTO>>searchClaimById(@PathVariable Integer claimId)
		{
		    List<ClaimDTO> result = this.claimService.searchClaim(claimId);		
		    return new ResponseEntity<List<ClaimDTO>>(result,HttpStatus.OK);
		}

}

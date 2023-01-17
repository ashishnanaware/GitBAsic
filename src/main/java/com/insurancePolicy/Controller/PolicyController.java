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
import com.insurancePolicy.Payloads.PolicyDTO;
import com.insurancePolicy.Service.PolicyService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/policy")
public class PolicyController {
	
	@Autowired
	private PolicyService policyService;
    @PostMapping("/")
	public ResponseEntity<PolicyDTO>createPolicy(@Valid @RequestBody PolicyDTO policyDto)
	{
		PolicyDTO createPolicyDto = this.policyService.createPolicy(policyDto);
		return new ResponseEntity<>(createPolicyDto,HttpStatus.CREATED);
	}
    @PutMapping("/{policyId}")
    public ResponseEntity<PolicyDTO>updatePolicy(@Valid @RequestBody PolicyDTO policyDto,@PathVariable("policyId") Integer pid)
    {
    	PolicyDTO updatePolicy = this.policyService.updatePolicy(policyDto, pid);
    	return ResponseEntity.ok(updatePolicy);
    }
    @DeleteMapping("/{policyId}")
    public ResponseEntity<ApiResponse>deletePolicy(@PathVariable("policyId") Integer pid)
    {
    	this.policyService.deletePolicy(pid);
    	return new ResponseEntity<ApiResponse>(new ApiResponse("Policy Deleted Succesfully",true),HttpStatus.OK);	
    }
    @GetMapping("/")
    public ResponseEntity<List<PolicyDTO>>getAllPolicy()
    {
    	return ResponseEntity.ok(this.policyService.getAllPolicy());
    }
    @GetMapping("/{policyId}")
    public ResponseEntity<PolicyDTO>getSinglePolicy(@PathVariable("policyId") Integer pid)
    {
    	return ResponseEntity.ok(this.policyService.getPolicyById(pid));
    }

}

package com.insurancePolicy.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.insurancePolicy.Entity.Policy;
import com.insurancePolicy.Exception.ResourceNotFoundException;
import com.insurancePolicy.Payloads.PolicyDTO;
import com.insurancePolicy.Repository.PolicyRepo;
import com.insurancePolicy.Service.PolicyService;

@Service
public class PolicyServiceImpl implements PolicyService {
	
	@Autowired
	private PolicyRepo repo;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PolicyDTO createPolicy(PolicyDTO policyDto) {
		Policy policy = this.dtoToPolicy(policyDto);
		Policy savedPolicy= this.repo.save(policy);
		return this.policyToDto(savedPolicy);
	}

	@Override
	public PolicyDTO updatePolicy(PolicyDTO policyDto, Integer policyId) {
		Policy policy = this.repo.findById(policyId).orElseThrow(()-> new ResourceNotFoundException("Policy"," policyId ",policyId));
		policy.setAmount(policyDto.getAmount());
		policy.setStartDate(policyDto.getStartDate());
		policy.setEndDate(policyDto.getEndDate());
		policy.setStatus(policyDto.isStatus());
		policy.setType(policyDto.getType());
		Policy updatedPolicy = this.repo.save(policy);
		PolicyDTO policyDTO = this.policyToDto(updatedPolicy);
		return policyDTO;
	}

	@Override
	public PolicyDTO getPolicyById(Integer policyId) {
		Policy policy = this.repo.findById(policyId).orElseThrow(()-> new ResourceNotFoundException("Policy"," PolicyId ",policyId));
		return this.policyToDto(policy);
	}

	@Override
	public List<PolicyDTO> getAllPolicy() {
		List<Policy>policies = this.repo.findAll();
		List<PolicyDTO>policyDto = policies.stream().map(policy->this.policyToDto(policy)).collect(Collectors.toList());
		return policyDto;
	}

	@Override
	public void deletePolicy(Integer policyId) {
		Policy policy = this.repo.findById(policyId).orElseThrow(()-> new ResourceNotFoundException("Policy"," policyId ",policyId));
		this.repo.delete(policy);

	}
	public Policy dtoToPolicy(PolicyDTO policyDto)
	{
		Policy policy = this.modelMapper.map(policyDto, Policy.class);
		return policy;
	}
	public PolicyDTO policyToDto(Policy policy)
	{
		PolicyDTO policyDto = this.modelMapper.map(policy, PolicyDTO.class);
		return policyDto;
	}

}

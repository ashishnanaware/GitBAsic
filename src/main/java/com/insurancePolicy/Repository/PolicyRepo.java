package com.insurancePolicy.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insurancePolicy.Entity.Policy;

public interface PolicyRepo extends JpaRepository<Policy, Integer> {

}

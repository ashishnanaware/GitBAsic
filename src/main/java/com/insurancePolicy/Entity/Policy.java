package com.insurancePolicy.Entity;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
@Table(name="policy")
public class Policy {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private int policyId;
	private double amount;
	private String type;
	private Boolean status;
	private Date startDate;
	private Date endDate;
	@OneToMany(cascade = CascadeType.ALL,mappedBy="policy", fetch = FetchType.LAZY) //
	@JsonIgnore
	private List<Customer> listofcustomers = new ArrayList<Customer>();
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy="policy", fetch = FetchType.LAZY) //
	@JsonIgnore
	private List<Claim> listofclaim = new ArrayList<Claim>();

}

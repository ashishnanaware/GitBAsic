package com.insurancePolicy.Repository;




import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insurancePolicy.Entity.User;

public interface UserRepo extends JpaRepository<User, Long>{
  Optional<User> findByUserName(String userName);
}

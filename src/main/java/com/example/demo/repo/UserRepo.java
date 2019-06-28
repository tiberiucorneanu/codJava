package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entities.User;

public interface UserRepo extends JpaRepository<User, Long> {
	
	@Query(value="SELECT * from User",nativeQuery=true)
	public List<User> getUseraasdasdas();
}

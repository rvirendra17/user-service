package com.in.fmc.userservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.in.fmc.userservice.entities.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, Integer>{

	Optional<Login> findByUsernameAndPassword(String username,String password);
	Optional<Login> findByUsername(String username);
}

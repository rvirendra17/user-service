package com.in.fmc.userservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.in.fmc.userservice.entities.Register;

@Repository
public interface RegisterRepository extends JpaRepository<Register, Integer> {

}

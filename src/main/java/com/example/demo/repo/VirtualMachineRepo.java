package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.VirtualMachine;

public interface VirtualMachineRepo extends JpaRepository<VirtualMachine, Long> {

}

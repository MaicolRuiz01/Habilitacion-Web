package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Clase;

public interface RepoClase extends JpaRepository<Clase, Integer> {
	
	Clase findByNuuid(String nuuid);
}

package com.example.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Jugador;
public interface RepoJugador extends JpaRepository<Jugador, Integer>{
	
	Jugador findByNombre(String nombre);
	Jugador findByNuuid(String nuuid);
}

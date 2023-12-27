package com.example.demo.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Clase implements Serializable{
	
	@Id
	@SequenceGenerator(name="clase_id_seq",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "clase_id_seq")
	private Integer id;
	private String descripcion;
	private String nuuid;
	@JsonIgnore
	@OneToMany(mappedBy = "clase", cascade = CascadeType.ALL)
	private List<Jugador> jugadores = new ArrayList<>();

}

package com.example.demo.entities;

import java.io.Serializable;
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
public class Rango implements Serializable{
	
	@Id
	@SequenceGenerator(name="rango_id_seq",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "rango_id_seq")
	private Integer id;
	private String descripcion;
	private String nuuid;
	@JsonIgnore
	@OneToMany(mappedBy = "rango", cascade = CascadeType.ALL)
	private List<Habilidad> habilidades = new ArrayList<>();
	@JsonIgnore
	@OneToMany(mappedBy = "rango", cascade = CascadeType.ALL)
	private List<Jugador> jugadores = new ArrayList<>();

}

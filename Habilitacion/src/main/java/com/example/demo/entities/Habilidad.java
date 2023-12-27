package com.example.demo.entities;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Habilidad implements Serializable{
	
	@Id
	@SequenceGenerator(name="habilidad_id_seq",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "habilidad_id_seq")
	private Integer id;
	private String nombre;
	private String descripcion;
	private String nuuid;
	
	@ManyToOne
	@JoinColumn(name="rango_id")
	private Rango rango;
	@JsonIgnore
	@ManyToMany
    @JoinTable(
        name = "habilidad_jugador",
        joinColumns = @JoinColumn(name = "habilidad_id"),
        inverseJoinColumns = @JoinColumn(name = "jugador_id")
    )
    private List<Jugador> jugadores;
	


}

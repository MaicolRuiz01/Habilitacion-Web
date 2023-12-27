package com.example.demo.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
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
public class Jugador implements Serializable{
	
	@Id
	@SequenceGenerator(name="jugador_id_seq",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "jugador_id_seq")
	private Integer id;
	private String nombre;
	private Date fecha_nacimiento;
	private String descripcion;
	@ManyToOne
	@JoinColumn(name="clase_id")
	private Clase clase;
	private String genero;
	@ManyToOne
	@JoinColumn(name="rango_id")
	private Rango rango;
	@JsonIgnore
	@ManyToMany(mappedBy = "jugadores")
    private List<Habilidad> habilidades;
	private String nuuid;
	

}

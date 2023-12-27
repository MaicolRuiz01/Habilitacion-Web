package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Clase;

import com.example.demo.entities.Habilidad;
import com.example.demo.entities.Jugador;
import java.util.ArrayList;



import com.example.demo.repository.RepoClase;
import com.example.demo.repository.RepoHabilidad;
import com.example.demo.repository.RepoJugador;

@RestController
@RequestMapping("/jugadores")
public class JugadorController {
	
	@Autowired
	RepoJugador repojugador;
	@Autowired
	RepoClase repoclase;
	@Autowired
	RepoHabilidad repohabi;
	
	@PostMapping("/")
	public ResponseEntity<Jugador> crearJugador(@RequestBody Jugador nuevoJugador) {
		Jugador jugadorExistente = repojugador.findByNombre(nuevoJugador.getNombre());

	    if (jugadorExistente != null) {
	        return ResponseEntity.badRequest().body(null);
	    }

	    Jugador jugadoradorCreado = repojugador.save(nuevoJugador);
	    return ResponseEntity.status(HttpStatus.CREATED).body(jugadoradorCreado);
	}
	
	@GetMapping("/lista")
	public ResponseEntity<List<Jugador>> listarJugadores() {
	    List<Jugador> jugadores = repojugador.findAll();

	    if (jugadores.isEmpty()) {
	        return ResponseEntity.noContent().build();
	    }

	    return ResponseEntity.ok(jugadores);
	}
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> obtenerNUUIDPorEmail(@RequestParam String nombre) {
	    Jugador jugador = repojugador.findByNombre(nombre);

	    if (jugador == null) {
	        return ResponseEntity.notFound().build();
	    }

	    Map<String, String> response = new HashMap<>();
	    response.put("nuuid", jugador.getNuuid());

	    return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{clase}")
	public ResponseEntity<List<Jugador>> listarugadoresPorClase(@PathVariable("clase") String claseNuuid){
		Clase claseEntrenador = repoclase.findByNuuid(claseNuuid);
		
		 if (claseEntrenador == null) {
		        return ResponseEntity.notFound().build();
		    }

		    List<Jugador> jugadores = claseEntrenador.getJugadores();

		    if (jugadores.isEmpty()) {
		        return ResponseEntity.notFound().build();
		    }

		    return ResponseEntity.ok(jugadores);
	}
	
	@GetMapping("/{nuuid}/habilidades")
	 public ResponseEntity<List<Habilidad>> listarHabilidadesDeJugador(@PathVariable("nuuid") String nuuid) {
		Jugador jugador = repojugador.findByNuuid(nuuid);

	     if (jugador == null) {
	         return ResponseEntity.notFound().build();
	     }

	     List<Habilidad> habilidades = jugador.getHabilidades();

	     return ResponseEntity.ok(habilidades);
	 }
	
	@PostMapping("/{nuuid}/habilidades/{nuuidHabilidad}")
	public ResponseEntity<String> agregarHabilidadAJugador(
	        @PathVariable("nuuid") String jugadorNuuid,
	        @PathVariable("nuuidHabilidad") String habilidadNuuid) {

	    Jugador jugador = repojugador.findByNuuid(jugadorNuuid);
	    Habilidad habilidad = repohabi.findByNuuid(habilidadNuuid);

	    if (jugador == null || habilidad == null) {
	        return ResponseEntity.notFound().build();
	    }

	    // Obtener o inicializar la lista de habilidades del jugador
	    List<Habilidad> habilidadesJugador = jugador.getHabilidades();
	    if (habilidadesJugador == null) {
	        habilidadesJugador = new ArrayList<>();
	    }

	    // Verifica si el jugador ya tiene la habilidad
	    if (!habilidadesJugador.contains(habilidad)) {
	        habilidadesJugador.add(habilidad);
	        jugador.setHabilidades(habilidadesJugador); // Actualiza la lista de habilidades en el jugador
	        repojugador.save(jugador); // Guarda los cambios en la base de datos
	        return ResponseEntity.ok().build();
	    }

	    return ResponseEntity.badRequest().body("El jugador ya tiene esta habilidad.");
	}

	
	 @PutMapping("/{nuuid}")
	    public ResponseEntity<Jugador> actualizarJugador(@PathVariable("nuuid") String nuuid, @RequestBody Jugador jugadorActualizado) {
	        Jugador jugador = repojugador.findByNuuid(nuuid);

	        if (jugador == null) {
	            return ResponseEntity.notFound().build();
	        }

	        // Actualizar los campos del jugador con la información proporcionada en jugadorActualizado
	        jugador.setNombre(jugadorActualizado.getNombre());
	        jugador.setFecha_nacimiento(jugadorActualizado.getFecha_nacimiento());
	        jugador.setDescripcion(jugadorActualizado.getDescripcion());
	        jugador.setClase(jugadorActualizado.getClase());
	        jugador.setGenero(jugadorActualizado.getGenero());
	        jugador.setRango(jugadorActualizado.getRango());
	        jugador.setHabilidades(jugadorActualizado.getHabilidades());
	        // Actualizar cualquier otro campo que necesites

	        Jugador jugadorGuardado = repojugador.save(jugador);
	        return ResponseEntity.ok(jugadorGuardado);
	    }


}

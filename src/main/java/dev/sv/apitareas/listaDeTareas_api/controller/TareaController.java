package dev.sv.apitareas.listaDeTareas_api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tareas")
public class TareaController {
	
	private List<String> listaDeTareas = new ArrayList<>();	
	
	public TareaController() {
		listaDeTareas.add("Pasear el perro");
		listaDeTareas.add("Trabajar");
	}
	
	@GetMapping
	public List<String> getTareas() {
		return listaDeTareas;
	}
	
	@PostMapping
	public String postTarea(@RequestBody String tarea) {
		listaDeTareas.add(tarea);
		
		return String.format("Tarea agregada: %s", tarea);
	}
	
	@DeleteMapping("/{indice}")
	public ResponseEntity<String> deleteTarea(@PathVariable int indice) {
		if(indice < 0 || indice >= listaDeTareas.size()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Índice inválido");
		}
		
		String deleted = listaDeTareas.remove(indice);
		
		return ResponseEntity.status(HttpStatus.OK).body(String.format("Tarea eliminada: %s", deleted));
	}
}

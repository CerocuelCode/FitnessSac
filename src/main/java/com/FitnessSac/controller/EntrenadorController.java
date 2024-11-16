package com.FitnessSac.controller;

import java.util.List;

import com.FitnessSac.entity.Plann;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.FitnessSac.entity.Entrenador;
import com.FitnessSac.repository.EntrenadorRepository;

@Controller

@RequestMapping("/entrenador")
public class EntrenadorController {

	@Autowired
	EntrenadorRepository entrenadorRepository;
	
	@PostMapping("/nuevo")
	public String nuevo(Model model) {
		Entrenador objEntrenador = new Entrenador();
		model.addAttribute("objEntrenador",objEntrenador);
		return "entrenador/nuevoEntrenador";
	}
	
	@PostMapping("/registrar")
	public String registrar(@ModelAttribute("objEntrenador")Entrenador entrenador, Model model) {
		entrenadorRepository.save(entrenador);
		
		List<Entrenador> listaEntrenadores = entrenadorRepository.findAll();
		model.addAttribute("listaEntrenadores", listaEntrenadores);
		return "entrenador/gestionEntrenador";
	}
	
	@RequestMapping(value="/actualizar", method=RequestMethod.POST)
	public String actualizar(@ModelAttribute("objEntrenador")Entrenador objEntrenador, Model model) {
		Entrenador objEntrenadorBD = entrenadorRepository.findById(objEntrenador.getId());
		objEntrenadorBD.setNombre(objEntrenador.getNombre());
		objEntrenadorBD.setApellidoPaterno(objEntrenador.getApellidoPaterno());
		objEntrenadorBD.setApellidoMaterno(objEntrenador.getApellidoMaterno());
		objEntrenadorBD.setFechaNacimiento(objEntrenador.getFechaNacimiento());
		objEntrenadorBD.setCorreo(objEntrenador.getCorreo());
		objEntrenadorBD.setEspecialidad(objEntrenador.getEspecialidad());
		objEntrenadorBD.setDisponibilidad(objEntrenador.getDisponibilidad());
		objEntrenadorBD.setFechaModificacion(objEntrenador.getFechaModificacion());
		objEntrenadorBD.setEstado(objEntrenador.getEstado());
		entrenadorRepository.save(objEntrenadorBD);
		return "redirect:/home/gestionEntrenador";				
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable("id")int id, Model model) {
		Entrenador objEntrenador = entrenadorRepository.findById(id);
		model.addAttribute("objEntrenador", objEntrenador);
		return "entrenador/gestionEntrenador";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable("id") int id, Model model) {
		entrenadorRepository.deleteById(id);
		List<Entrenador> listaEntrenadores = entrenadorRepository.findAll();
		model.addAttribute("listaEntrenadores", listaEntrenadores);
		return "entrenador/gestionEntrenador";
	}
	
}

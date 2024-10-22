package com.FitnessSac.controller;

import java.util.List;

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
		return "nuevoEntrenador";
	}
	
	@PostMapping("/registrar")
	public String registrar(@ModelAttribute("objEntrenador")Entrenador entrenador, Model model) {
		entrenadorRepository.save(entrenador);
		
		List<Entrenador> listaEntrenadores = entrenadorRepository.findAll();
		model.addAttribute("listaEntrenadores", listaEntrenadores);
		return "gestionEntrenador";
	}
	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable("id")int id, Model model) {
		Entrenador objEntrenador = entrenadorRepository.findById(id);
		model.addAttribute("objEntrenador", objEntrenador);
		return "editarEntrenador";
	}
	
	@RequestMapping(value="/actualizar", method=RequestMethod.POST)
	public String actualizar(@ModelAttribute("objEntrenador")Entrenador objEntrenador, Model model) {
		Entrenador objEntrenadorBD = entrenadorRepository.findById(objEntrenador.getId());
		objEntrenadorBD.setNombres(objEntrenador.getNombres());
		objEntrenadorBD.setApellidos(objEntrenador.getApellidos());
		objEntrenadorBD.setEspecialidad(objEntrenador.getEspecialidad());
		objEntrenadorBD.setContacto(objEntrenador.getContacto());
		objEntrenadorBD.setDisponibilidad(objEntrenador.getDisponibilidad());
		entrenadorRepository.save(objEntrenadorBD);
		return "redirect:/home/gestionEntrenador";				
	}
	
}

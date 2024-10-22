package com.FitnessSac.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.FitnessSac.entity.Entrenador;
import com.FitnessSac.repository.EntrenadorRepository;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	EntrenadorRepository entrenadorRepository;
	
	@GetMapping("/gestionEntrenador")
	public String getionEntrenador(Model model) {
		List<Entrenador> listaEntrenadores = entrenadorRepository.findAll();
		model.addAttribute("listaEntrenadores", listaEntrenadores);
		return "gestionEntrenador";
	}

}

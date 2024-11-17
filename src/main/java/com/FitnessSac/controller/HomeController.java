package com.FitnessSac.controller;

import java.util.List;

import com.FitnessSac.entity.*;
import com.FitnessSac.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	EntrenadorRepository entrenadorRepository;
    @Autowired
	PlannRepository planRepository;
	@Autowired
	ReservaRepository reservaRepository;
	@Autowired
	UsuarioRepository usuarioRepository;
	@Autowired
	EntrenamientoRepository entrenamientoRepository;
	@Autowired
	InstalacionRepository instalacionRepository;


	@GetMapping("/gestionEntrenador")
	public String getionEntrenador(Model model) {
		List<Entrenador> listaEntrenadores = entrenadorRepository.findAll();
		model.addAttribute("listaEntrenadores", listaEntrenadores);
		return "entrenador/gestionEntrenador";
	}

	@GetMapping("/gestionEntrenamiento")
	public String gestionEntrenamiento(Model model) {
		List<Entrenamiento> listaEntrenamientos = entrenamientoRepository.findAllByEstado("ACTIVO");
		model.addAttribute("listaEntrenamientos",listaEntrenamientos);
		List<Entrenador> listaEntrenadores = entrenadorRepository.findAll();
		model.addAttribute("listaEntrenadores",listaEntrenadores);
		List<Instalacion> listaInstalaciones = instalacionRepository.findAll();
		model.addAttribute("listaInstalaciones",listaInstalaciones);
		return "entrenamiento/gestionEntrenamiento";
	}

	@GetMapping("/gestionPlan")
	public String gestionPlan(Model model) {
		List<Plann> listaPlanes = planRepository.findAllByEstado("ACTIVO");
		model.addAttribute("listaPlanes", listaPlanes);
		return "plan/gestionPlan";
	}

	@GetMapping("/gestionReserva")
	public String gestionReserva(Model model) {

		List<Reserva> listaReservas = reservaRepository.findAllByEstado("ACTIVO");
		model.addAttribute("listaReservas", listaReservas);
		List<Usuario> listaUsuarios = usuarioRepository.findAll();
		model.addAttribute("listaUsuarios", listaUsuarios);
		List<Entrenamiento> listaEntrenamientos = entrenamientoRepository.findAll();
		model.addAttribute("listaEntrenamientos", listaEntrenamientos);
		return "reserva/gestionReserva";
	}

}

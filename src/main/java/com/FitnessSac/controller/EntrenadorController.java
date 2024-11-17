package com.FitnessSac.controller;

import java.util.List;

import com.FitnessSac.entity.Plann;
import com.FitnessSac.repository.EntrenamientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.FitnessSac.entity.Entrenador;
import com.FitnessSac.repository.EntrenadorRepository;

@Controller

@RequestMapping("/entrenador")
public class EntrenadorController {

	@Autowired
	EntrenadorRepository entrenadorRepository;

	@Autowired
	EntrenamientoRepository entrenamientoRepository;

	@PostMapping("/nuevo")
	public String nuevo(Model model) {
		Entrenador objEntrenador = new Entrenador();
		model.addAttribute("objEntrenador",objEntrenador);
		return "entrenador/nuevoEntrenador";
	}

	@PostMapping("/registrar")
	public String registrar(@ModelAttribute("objEntrenador")Entrenador entrenador, Model model) {
		entrenadorRepository.save(entrenador);

		List<Entrenador> listaEntrenadores = entrenadorRepository.findAllByEstado("ACTIVO");
		model.addAttribute("listaEntrenadores", listaEntrenadores);
		return "entrenador/gestionEntrenador";
	}

	@GetMapping("/buscar")
	public String buscarByNombre(@RequestParam(value = "nombre") String nombre, Model model) {
		List<Entrenador> listaEntrenadores;

		if (nombre != null && !nombre.isEmpty()) {
			listaEntrenadores = entrenadorRepository.findByNombreContainingIgnoreCaseAndEstado(nombre, "ACTIVO");
		} else {
			listaEntrenadores = entrenadorRepository.findAllByEstado("ACTIVO");
		}

		model.addAttribute("listaEntrenadores", listaEntrenadores);
		model.addAttribute("nombre", nombre);
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

		List<Entrenador> listaEntrenadores = entrenadorRepository.findAllByEstado("ACTIVO");
		model.addAttribute("listaEntrenadores", listaEntrenadores);
		return "redirect:/home/gestionEntrenador";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable("id")int id, Model model) {
		Entrenador objEntrenador = entrenadorRepository.findById(id);
		model.addAttribute("objEntrenador", objEntrenador);
		return "entrenador/editarEntrenador";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable("id") int id, Model model) {
		Entrenador entrenador = entrenadorRepository.findById(id);

		if (entrenamientoRepository.existsByEntrenador_idAndEstado(id, "ACTIVO")) {
			model.addAttribute("error", "No se puede eliminar el entrenador con ID " + id + " porque tiene entrenamientos asociados.");
		} else {
			if(entrenador != null) {
				entrenador.setEstado("INACTIVO");
				entrenadorRepository.save(entrenador);
			} else{
				model.addAttribute("message", "El entrenador con ID " + id + " no fue encontrado.");
			}
		}

		List<Entrenador> listaEntrenadores = entrenadorRepository.findAllByEstado("ACTIVO");
		model.addAttribute("listaEntrenadores", listaEntrenadores);

		return "entrenador/gestionEntrenador";
	}
	
}

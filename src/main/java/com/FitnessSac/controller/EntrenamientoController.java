package com.FitnessSac.controller;

import com.FitnessSac.entity.Entrenador;
import com.FitnessSac.entity.Entrenamiento;
import com.FitnessSac.entity.Instalacion;
import com.FitnessSac.repository.EntrenadorRepository;
import com.FitnessSac.repository.EntrenamientoRepository;
import com.FitnessSac.repository.InstalacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/entrenamiento")
public class EntrenamientoController {
    @Autowired
    EntrenamientoRepository entrenamientoRepository;

    @Autowired
    EntrenadorRepository entrenadorRepository;

    @Autowired
    InstalacionRepository instalacionRepository;

    @PostMapping("/nuevo")
    public String nuevo(Model model) {
        Entrenamiento objEntrenamiento = new Entrenamiento();
        model.addAttribute("objEntrenamiento",objEntrenamiento);

        List<Entrenador> listaEntrenadores = entrenadorRepository.findAllByEstado("ACTIVO");
        model.addAttribute("listaEntrenadores",listaEntrenadores);

        List<Instalacion> listaInstalaciones = instalacionRepository.findAllByEstado("ACTIVO");
        model.addAttribute("listaInstalaciones",listaInstalaciones);

        return "entrenamiento/nuevoEntrenamiento";
    }

    @RequestMapping("/buscar")
    public String buscar(@RequestParam("entrenador") String entrenador, Model model) {
        List<Entrenamiento> listaEntrenamientos;

        if (entrenador != null && !entrenador.trim().isEmpty()) {
            List<Entrenador> listaEntrenadores = entrenadorRepository.findByNombreContainingAndEstado(entrenador, "ACTIVO");

            listaEntrenamientos = entrenamientoRepository.findByEntrenadorInAndEstado(listaEntrenadores, "ACTIVO");

            if (listaEntrenadores.isEmpty()) {
                model.addAttribute("message", "No se encontraron entrenadores con ese nombre.");
            }
        } else {
            listaEntrenamientos = entrenamientoRepository.findAllByEstado("ACTIVO");
        }

        model.addAttribute("listaEntrenamientos", listaEntrenamientos);

        model.addAttribute("listaEntrenadores", entrenadorRepository.findAllByEstado("ACTIVO"));

        return "entrenamiento/gestionEntrenamiento";
    }

    @PostMapping("/registrar")
    public String registrar(@ModelAttribute("objEntrenamiento") Entrenamiento entrenamiento, Model model) {
        entrenamiento.setCupoDisponible(entrenamiento.getCupoMaximo());
        entrenamientoRepository.save(entrenamiento);

        List<Entrenamiento> listaEntrenamientos = entrenamientoRepository.findAllByEstado("ACTIVO");
        model.addAttribute("listaEntrenamientos", listaEntrenamientos);

        return "entrenamiento/gestionEntrenamiento";
    }

    @RequestMapping(value="/actualizar", method= RequestMethod.POST)
    public String actualizar(@ModelAttribute("objEntrenamiento")Entrenamiento objEntrenamiento, Model model) {
        Entrenamiento objEntrenamientoBD = entrenamientoRepository.findById(objEntrenamiento.getId());

        objEntrenamientoBD.setEntrenador(objEntrenamiento.getEntrenador());
        objEntrenamientoBD.setInstalacion(objEntrenamiento.getInstalacion());
        objEntrenamientoBD.setFecha(objEntrenamiento.getFecha());
        objEntrenamientoBD.setHoraInicio(objEntrenamiento.getHoraInicio());
        objEntrenamientoBD.setHoraFin(objEntrenamiento.getHoraFin());
        objEntrenamientoBD.setTipoEntrenamiento(objEntrenamiento.getTipoEntrenamiento());
        objEntrenamientoBD.setHoraInicio(objEntrenamiento.getHoraInicio());
        objEntrenamientoBD.setCupoMaximo(objEntrenamiento.getCupoMaximo());

        entrenamientoRepository.save(objEntrenamientoBD);

        List<Entrenamiento> listaEntrenamientos = entrenamientoRepository.findAllByEstado("ACTIVO");
        model.addAttribute("listaEntrenamientos", listaEntrenamientos);

        return "redirect:/home/gestionEntrenamiento";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id")int id, Model model) {
        Entrenamiento objEntrenamiento = entrenamientoRepository.findById(id);
        model.addAttribute("objEntrenamiento", objEntrenamiento);

        List<Entrenador> listaEntrenadores = entrenadorRepository.findAllByEstado("ACTIVO");
        model.addAttribute("listaEntrenadores",listaEntrenadores);

        List<Instalacion> listaInstalaciones = instalacionRepository.findAllByEstado("ACTIVO");
        model.addAttribute("listaInstalaciones",listaInstalaciones);
        return "entrenamiento/editarEntrenamiento";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") int id, Model model) {
        Entrenamiento entrenamiento = entrenamientoRepository.findById(id);

        if (entrenamiento != null) {
            entrenamiento.setEstado("INACTIVO");
            entrenamientoRepository.save(entrenamiento);
        } else {
            model.addAttribute("message", "El entrenamiento con ID " + id + " no fue encontrado.");
        }

        List<Entrenamiento> listaEntrenamientos = entrenamientoRepository.findAllByEstado("ACTIVO");
        model.addAttribute("listaEntrenamientos", listaEntrenamientos);

        return "entrenamiento/gestionEntrenamiento";
    }



}

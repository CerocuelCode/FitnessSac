package com.FitnessSac.controller;

import com.FitnessSac.entity.Entrenamiento;
import com.FitnessSac.entity.Reserva;
import com.FitnessSac.entity.Usuario;
import com.FitnessSac.repository.EntrenadorRepository;
import com.FitnessSac.repository.EntrenamientoRepository;
import com.FitnessSac.repository.ReservaRepository;
import com.FitnessSac.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/reserva")
public class ReservaController {
    @Autowired
    ReservaRepository reservaRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    EntrenamientoRepository entrenamientoRepository;

    @PostMapping("/nuevo")
    public String nuevo(Model model) {
        Reserva objReserva = new Reserva();
        model.addAttribute("objReserva", objReserva);
        List<Usuario> listaUsuarios = usuarioRepository.findAll();
        model.addAttribute("listaUsuarios", listaUsuarios);
        List<Entrenamiento> listaEntrenamientos = entrenamientoRepository.findAll();
        model.addAttribute("listaEntrenamientos", listaEntrenamientos);
        return "reserva/nuevoReserva";
    }

    @GetMapping("/buscar") public String buscar(@RequestParam("usuarioId") int usuarioId, Model model) {
        Usuario usuario = usuarioRepository.findById(usuarioId);
        List<Reserva> listaReservas = reservaRepository.findByUsuarioAndEstado(usuario, "ACTIVO");
        model.addAttribute("listaReservas", listaReservas);
        model.addAttribute("usuario", usuario); List<Usuario> listaUsuarios = usuarioRepository.findAllByEstado("ACTIVO");
        model.addAttribute("listaUsuarios", listaUsuarios);
        return "reserva/gestionReserva"; }

    @PostMapping("/registrar")
    public String registrar(@ModelAttribute("objReserva") Reserva reserva, Model model) {

        List<Reserva> listaReservas = reservaRepository.findAllByEstado("ACTIVO");
        model.addAttribute("listaReservas", listaReservas);

        reservaRepository.save(reserva);

        return "reserva/gestionReserva";
    }



    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute("objReserva")Reserva reserva, Model model) {
        Reserva reservaBD = reservaRepository.findById(reserva.getId());

        if(reservaBD!=null) {
            reservaBD.setUsuario(reserva.getUsuario());
            reservaBD.setEntrenamiento(reserva.getEntrenamiento());
            reservaBD.setEstadoReserva(reserva.getEstadoReserva());
            reservaBD.setFechaReserva(reserva.getFechaReserva());
            reservaBD.setFechaModificacion(reserva.getFechaModificacion());
            reservaBD.setEstado(reserva.getEstado());
            reservaRepository.save(reservaBD);
        }

        List<Reserva> listaReservas = reservaRepository.findAllByEstado("ACTIVO");
        model.addAttribute("listaReservas", listaReservas);

        return "reserva/gestionReserva";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Reserva reserva, Model model) {
        Reserva reservaBD = reservaRepository.findById(reserva.getId());
        model.addAttribute("objReserva", reservaBD);
        List<Usuario> listaUsuarios = usuarioRepository.findAllByEstado("ACTIVO");
        model.addAttribute("listaUsuarios", listaUsuarios);
        List<Entrenamiento> listaEntrenamientos = entrenamientoRepository.findAllByEstado("ACTIVO");
        model.addAttribute("listaEntrenamientos", listaEntrenamientos);
        return "reserva/editarReserva";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") int id, Model model) {
        Reserva reserva = reservaRepository.findById(id);

        if (reserva != null) {
            reserva.setEstado("INACTIVO");
            reservaRepository.save(reserva);
        } else {
            model.addAttribute("message", "El plan con ID " + id + " no fue encontrado.");
        }

        List<Reserva> listaReservas = reservaRepository.findAllByEstado("ACTIVO");
        model.addAttribute("listaReservas", listaReservas);
        return "reserva/gestionReserva";
    }
}

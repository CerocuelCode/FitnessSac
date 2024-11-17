package com.FitnessSac.controller;

import com.FitnessSac.entity.Entrenador;
import com.FitnessSac.entity.Plann;
import com.FitnessSac.repository.PlannRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/plan")
public class PlanesController {

    @Autowired
    PlannRepository planRepository;

    @PostMapping("/nuevo")
    public String nuevo(Model model) {
        Plann objPlan = new Plann();
        model.addAttribute("objPlan", objPlan);
        return "plan/nuevoPlan";
    }

    @PostMapping("/registrar")
    public String registrar(@ModelAttribute("objPlan") Plann plan, Model model) {
        planRepository.save(plan);
        List<Plann> listaPlanes = planRepository.findAllByEstado("ACTIVO");
        model.addAttribute("listaPlanes", listaPlanes);
        return "plan/gestionPlan";
    }

    @GetMapping("/buscar")
    public String buscarByNombre(@RequestParam(value = "nombre") String nombre, Model model) {
        List<Plann> listaPlanes;

        if (nombre != null && !nombre.isEmpty()) {
            listaPlanes = planRepository.findByNombreContainingIgnoreCaseAndEstado(nombre, "ACTIVO");
        } else {
            listaPlanes = planRepository.findAllByEstado("ACTIVO");
        }

        model.addAttribute("listaPlanes", listaPlanes);
        model.addAttribute("nombre", nombre);
        return "plan/gestionPlan";
    }

    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute("objPlan")Plann plan, Model model) {
        Plann planBD = planRepository.findById(plan.getId());

        planBD.setNombre(plan.getNombre());
        planBD.setDescripcion(plan.getDescripcion());
        planBD.setDuracion(plan.getDuracion());
        planBD.setEntrenamiento(plan.getEntrenamiento());
        planBD.setTipoEntrenamiento(plan.getTipoEntrenamiento());
        planBD.setPrecio(plan.getPrecio());
        planBD.setFechaModificacion(plan.getFechaModificacion());
        planBD.setEstado(plan.getEstado());
        planRepository.save(planBD);

        List<Plann> listaPlanes = planRepository.findAllByEstado("ACTIVO");
        model.addAttribute("listaPlanes", listaPlanes);
        return "plan/gestionPlan";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") int id, Model model) {
        Plann planBD = planRepository.findById(id);
        model.addAttribute("objPlan", planBD);
        return "plan/editarPlan";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") int id, Model model) {
        // Buscar el plan por id
        Plann plan = planRepository.findById(id);

        //Verificar si el plan es nulo
        if(plan != null) {
            plan.setEstado("INACTIVO"); // Cambia el estado a "INACTIVO"
            planRepository.save(plan);  // Guarda los cambios
        } else{
            //Manejar el caso deonde el plan no se encuentra
            model.addAttribute("message", "El plan con ID " + id + " no fue encontrado.");
        }

        // Actualizar la lista de planes y agregarlos al modelo
        List<Plann> listaPlanes = planRepository.findAllByEstado("ACTIVO");
        model.addAttribute("listaPlanes", listaPlanes);
        return "plan/gestionPlan";
    }

}

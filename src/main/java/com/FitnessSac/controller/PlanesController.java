package com.FitnessSac.controller;

import com.FitnessSac.entity.Entrenador;
import com.FitnessSac.entity.Planes;
import com.FitnessSac.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/plan")
public class PlanesController {
    @Autowired
    PlanRepository planRepository;

    @PostMapping("/nuevo")
    public String nuevo(Model model) {
        Planes objPlan = new Planes();
        model.addAttribute("objPlan", objPlan);
        return "nuevoPlan";
    }

    @PostMapping("/registrar")
    public String registrar(@ModelAttribute("objPlan")Planes plan, Model model) {
        planRepository.save(plan);
        List<Planes> listaPlanes = planRepository.findAll();
        model.addAttribute("listaPlanes", listaPlanes);
        return "gestionPlan";
    }

}

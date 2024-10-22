package com.FitnessSac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.FitnessSac.entity.Usuario;
import com.FitnessSac.repository.UsuarioRepository;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@RequestMapping(value = "/validarUsuario", method = RequestMethod.POST)
	public String validarUsuario(@RequestParam("correo")String correo, @RequestParam("password")String password) {
		String pagina = "";
		Usuario usuario = usuarioRepository.findByCorreoAndPassword(correo, password);
		if (usuario == null) {
			pagina = "index";
		} else {
			pagina = "principal";
		}
		return pagina;
	}
}

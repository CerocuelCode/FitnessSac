package com.FitnessSac.repository;

import com.FitnessSac.entity.Plann;
import com.FitnessSac.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.FitnessSac.entity.Usuario;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
	Usuario findByCorreoAndPassword(String correo, String password);
	List<Usuario> findAll();
	void deleteById(int id);
	Usuario findById(int id);

	List<Usuario> findAllByEstado(String estado);

	List<Usuario> findByNombreContainingIgnoreCaseAndEstado(String nombre, String estado);
	List<Usuario> findByNombreContainingAndEstado(String nombre, String estado);
}

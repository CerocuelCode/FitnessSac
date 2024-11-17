package com.FitnessSac.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.FitnessSac.entity.Entrenador;

@Repository
public interface EntrenadorRepository extends JpaRepository<Entrenador,Integer> {
	List<Entrenador> findAll();
	void deleteById(int id);
	Entrenador findById(int id);
	List<Entrenador> findAllByEstado(String estado);
	List<Entrenador> findByNombreContainingIgnoreCaseAndEstado(String nombre, String estado);
	List<Entrenador> findByNombreContainingAndEstado(String nombre, String estado);
}

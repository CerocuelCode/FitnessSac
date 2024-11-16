package com.FitnessSac.repository;

import com.FitnessSac.entity.Entrenamiento;
import com.FitnessSac.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntrenamientoRepository extends JpaRepository<Entrenamiento, Integer> {
    List<Entrenamiento> findAll();
    void deleteById(int id);
    Entrenamiento findById(int id);

    List<Entrenamiento> findAllByEstado(String estado);

}

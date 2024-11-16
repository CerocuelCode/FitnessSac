package com.FitnessSac.repository;

import com.FitnessSac.entity.Reserva;
import com.FitnessSac.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
    List<Reserva> findAll();
    void deleteById(int id);
    Reserva findById(int id);

    List<Reserva> findAllByEstado(String estado);
}

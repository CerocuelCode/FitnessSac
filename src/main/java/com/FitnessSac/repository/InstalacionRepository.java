package com.FitnessSac.repository;

import com.FitnessSac.entity.Instalacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstalacionRepository extends JpaRepository<Instalacion, Integer> {
    List<Instalacion> findAll();
    void deleteById(int id);
    Instalacion findById(int id);
}

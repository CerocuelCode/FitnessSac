package com.FitnessSac.repository;

import com.FitnessSac.entity.Sancion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SancionRepository extends JpaRepository<Sancion, Integer> {
    List<Sancion> findAll();
    void deleteById(int id);
    Sancion findById(int id);
}

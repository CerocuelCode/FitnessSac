package com.FitnessSac.repository;

import com.FitnessSac.entity.Plann;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlannRepository extends JpaRepository<Plann, Integer> {
    List<Plann> findAll();
    void deleteById(int id);
    Plann findById(int id);

    List<Plann> findAllByEstado(String estado);
}

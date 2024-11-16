package com.FitnessSac.repository;

import com.FitnessSac.entity.Reclamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReclamoRepository extends JpaRepository<Reclamo, Integer> {
    List<Reclamo> findAll();
    void deleteById(int id);
    Reclamo findById(int id);
}

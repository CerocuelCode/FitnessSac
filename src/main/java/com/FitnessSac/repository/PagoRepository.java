package com.FitnessSac.repository;

import com.FitnessSac.entity.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Integer> {
    List<Pago> findAll();
    void deleteById(int id);
    Pago findById(int id);
}

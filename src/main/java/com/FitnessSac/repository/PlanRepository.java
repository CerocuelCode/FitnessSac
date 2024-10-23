package com.FitnessSac.repository;

import java.util.List;

import com.FitnessSac.entity.Planes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PlanRepository extends JpaRepository<Planes,Integer> {
	List<Planes> findAll();
	Planes findById(int id);
}

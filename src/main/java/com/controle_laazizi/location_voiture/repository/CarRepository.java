package com.controle_laazizi.location_voiture.repository;

import com.controle_laazizi.location_voiture.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>{



}

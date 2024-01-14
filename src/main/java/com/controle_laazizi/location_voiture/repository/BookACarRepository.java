package com.controle_laazizi.location_voiture.repository;

import com.controle_laazizi.location_voiture.dto.BookACarDto;
import com.controle_laazizi.location_voiture.entity.BookACar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookACarRepository extends JpaRepository<BookACar, Long> {
    List<BookACar> findAllByUserId(Long userId);
}

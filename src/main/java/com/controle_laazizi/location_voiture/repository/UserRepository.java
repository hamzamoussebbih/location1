package com.controle_laazizi.location_voiture.repository;

import com.controle_laazizi.location_voiture.entity.User;
import com.controle_laazizi.location_voiture.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findFirstByEmail(String email);


    User findByUserRole(UserRole userRole);
}

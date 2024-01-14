package com.controle_laazizi.location_voiture.dto;

import com.controle_laazizi.location_voiture.enums.UserRole;
import lombok.Data;

@Data
public class AuthenticationResponse {

    private String jwt;

    private UserRole userRole;

    private Long userId;
}

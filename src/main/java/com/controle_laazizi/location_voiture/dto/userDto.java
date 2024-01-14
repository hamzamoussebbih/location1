package com.controle_laazizi.location_voiture.dto;

import com.controle_laazizi.location_voiture.enums.UserRole;
import lombok.Data;

@Data
public class userDto {

    private  Long id;

    private String name;

    private  String email;

    private UserRole userRole;
}

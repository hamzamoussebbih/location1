package com.controle_laazizi.location_voiture.services.auth;

import com.controle_laazizi.location_voiture.dto.SignupRequest;
import com.controle_laazizi.location_voiture.dto.userDto;

public interface AuthService {

    userDto createCustomer(SignupRequest signupRequest);

    boolean hasCustomerWithEmail(String email);
}

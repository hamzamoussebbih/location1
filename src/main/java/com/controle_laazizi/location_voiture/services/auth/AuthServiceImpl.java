package com.controle_laazizi.location_voiture.services.auth;

import com.controle_laazizi.location_voiture.dto.SignupRequest;
import com.controle_laazizi.location_voiture.dto.userDto;
import com.controle_laazizi.location_voiture.entity.User;
import com.controle_laazizi.location_voiture.enums.UserRole;
import com.controle_laazizi.location_voiture.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    @PostConstruct
    public void createAdminAccount(){
        User adminAccount = userRepository.findByUserRole(UserRole.ADMIN);
        if (adminAccount == null){
            User newAdminAccount = new User();
            newAdminAccount.setName("Admin");
            newAdminAccount.setEmail("admin@test.com");
            newAdminAccount.setPassword(new BCryptPasswordEncoder().encode("admin")
            );
            newAdminAccount.setUserRole(UserRole.ADMIN);
            userRepository.save(newAdminAccount);
            System.out.println("Admin account created succefully");
        }
    }

    @Override
    public userDto createCustomer(SignupRequest signupRequest){
        User user= new User();
        user.setName(signupRequest.getName());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        user.setEmail(signupRequest.getEmail());
        user.setUserRole(UserRole.CUSTOMER);
        User createUser = userRepository.save(user);
        userDto userDto = new userDto();
        userDto.setId(createUser.getId());
        return userDto;
    }
    @Override
    public boolean hasCustomerWithEmail(String email){
        return userRepository.findFirstByEmail(email).isPresent();
    }
}

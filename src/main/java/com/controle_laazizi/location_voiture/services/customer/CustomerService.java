package com.controle_laazizi.location_voiture.services.customer;

import com.controle_laazizi.location_voiture.dto.BookACarDto;
import com.controle_laazizi.location_voiture.dto.CarDto;
import com.controle_laazizi.location_voiture.entity.Car;
import com.controle_laazizi.location_voiture.enums.BookCarStatus;

import java.util.List;

public interface CustomerService {

    List<CarDto> getAllCars();

    boolean bookACar(BookACarDto bookACarDto);

    CarDto getCatById(Long carId);

    List<BookACarDto> getBookingsByUserId(Long userId);
}

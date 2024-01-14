package com.controle_laazizi.location_voiture.services.admin;

import com.controle_laazizi.location_voiture.dto.BookACarDto;
import com.controle_laazizi.location_voiture.dto.CarDto;
import com.controle_laazizi.location_voiture.dto.CarDtoListDto;
import com.controle_laazizi.location_voiture.dto.SearchCarDto;

import java.io.IOException;
import java.util.List;

public interface AdminService {

    boolean postCar(CarDto carDto) throws IOException;

    List<CarDto> getAllCars();

    void deleteCar(Long id);

    CarDto getCarById(Long id);

    boolean updateCar(Long carId, CarDto carDto) throws IOException;

    List<BookACarDto> getBookings();

    boolean changeBookingStatus(Long bookingId, String status);

    CarDtoListDto searchCar(SearchCarDto searchCarDto);



}

package com.controle_laazizi.location_voiture.controller;

import com.controle_laazizi.location_voiture.dto.BookACarDto;
import com.controle_laazizi.location_voiture.dto.CarDto;
import com.controle_laazizi.location_voiture.services.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class customerController {
   private final CustomerService customerService;

   @GetMapping("/cars")
   public ResponseEntity<List<CarDto>> getAllCars(){
      List<CarDto> carDtoList = customerService.getAllCars();
      return ResponseEntity.ok(carDtoList);
   }

   @PostMapping("/car/book")
   public ResponseEntity<Void> BookACar(@RequestBody BookACarDto bookACarDto){
       boolean success =  customerService.bookACar(bookACarDto);
       if(success) return ResponseEntity.status(HttpStatus.CREATED).build();
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

   }

   @GetMapping("/car/{carId}")
   public ResponseEntity<CarDto> getCarById(@PathVariable Long carId){
      CarDto carDto = customerService.getCatById(carId);

      if(carDto == null) return ResponseEntity.notFound().build();
      return ResponseEntity.ok(carDto);
   }
   @GetMapping("/car/bookings/{userId}")
   public ResponseEntity<List<BookACarDto>> getBookingsByUserId(@PathVariable Long userId){
      return ResponseEntity.ok(customerService.getBookingsByUserId(userId));
   }
}

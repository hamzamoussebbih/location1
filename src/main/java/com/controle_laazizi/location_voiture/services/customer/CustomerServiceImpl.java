package com.controle_laazizi.location_voiture.services.customer;

import com.controle_laazizi.location_voiture.dto.BookACarDto;
import com.controle_laazizi.location_voiture.dto.CarDto;
import com.controle_laazizi.location_voiture.entity.BookACar;
import com.controle_laazizi.location_voiture.entity.Car;
import com.controle_laazizi.location_voiture.entity.User;
import com.controle_laazizi.location_voiture.enums.BookCarStatus;
import com.controle_laazizi.location_voiture.repository.BookACarRepository;
import com.controle_laazizi.location_voiture.repository.CarRepository;
import com.controle_laazizi.location_voiture.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final CarRepository carRepository;

    private final UserRepository userRepository;

    private final BookACarRepository bookACarRepository;
    @Override
    public List<CarDto> getAllCars() {
        return carRepository.findAll().stream(). map(Car::getCarDto).collect(Collectors.toList());
    }

    @Override
    public boolean bookACar(BookACarDto bookACarDto) {
        Optional<Car> optionalCar = carRepository.findById(bookACarDto.getCarId());
        Optional<User> optionalUser = userRepository.findById(bookACarDto.getUserId());

        if (optionalCar.isPresent() && optionalUser.isPresent()) {
            Car existingCar = optionalCar.get();
            BookACar bookACar = new BookACar();
            bookACar.setUser(optionalUser.get());
            bookACar.setCar(optionalCar.get());
            bookACar.setBookCarStatus(BookCarStatus.PENDING);

            Date fromDate = bookACarDto.getFromDate();
            Date toDate = bookACarDto.getToDate();

            if (fromDate != null && toDate != null) {
                long diffInMilliSeconds = toDate.getTime() - fromDate.getTime();
                long days = TimeUnit.MILLISECONDS.toDays(diffInMilliSeconds);
                bookACar.setDays(days);
                bookACar.setPrice(existingCar.getPrice() * days);
            } else {
                // Handle the case where fromDate or toDate is null
                // You might want to log an error or throw an exception
                return false;
            }

            bookACar.setFromDate(fromDate);
            bookACar.setToDate(toDate);

            bookACarRepository.save(bookACar);
            return true;
        }

        return false;
    }


    @Override
    public CarDto getCatById(Long carId) {
        Optional<Car> optionalCar = carRepository.findById(carId);
        return optionalCar.map(Car::getCarDto).orElse(null) ;
    }

    @Override
    public List<BookACarDto> getBookingsByUserId(Long userId) {
        return bookACarRepository.findAllByUserId(userId).stream().map(BookACar::getBookACarDto).collect(Collectors.toList());
    }


}


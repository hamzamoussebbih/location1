package com.controle_laazizi.location_voiture.entity;

import com.controle_laazizi.location_voiture.dto.CarDto;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Base64;
import java.util.Date;

@Entity
@Data
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String color;
    private String name;
    private String type;
    private String transmission;
    private String description;
    private Long price;
    private Date year;

    @Column(columnDefinition = "longblob")
    private byte[] image;

    public CarDto getCarDto() {
        CarDto carDto = new CarDto();
        carDto.setId(id);
        carDto.setName(name);
        carDto.setBrand(brand);
        carDto.setColor(color);
        carDto.setPrice(price);
        carDto.setDescription(description);
        carDto.setType(type);
        carDto.setTransmission(transmission);
        carDto.setYear(year);

        if (image != null) {
            String base64Image = Base64.getEncoder().encodeToString(image);
            carDto.setReturnedImage(image);
        }

        return carDto;
    }
}

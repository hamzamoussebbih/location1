package com.controle_laazizi.location_voiture.dto;

import com.controle_laazizi.location_voiture.enums.BookCarStatus;
import lombok.Data;

import java.util.Date;
@Data

public class BookACarDto {

    private Long id;


    private Date fromDate;

    private Date toDate;

    private Long days;

    private Long price;

    private BookCarStatus bookCarStatus;

    private Long carId;

    private Long userId;

    private String username;

    private String email;
}

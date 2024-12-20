package com.example.SerVi.dto;
import com.example.SerVi.enums.ReservationStatus;
import com.example.SerVi.enums.ReviewStatus;
import lombok.Data;

import java.util.Date;

@Data
public class ReservationDto {

    private int id;

    private Date bookDate;

    private String serviceName;

    private ReservationStatus reservationStatus;

    private ReviewStatus reviewStatus;

    private int userId;

    private String userName;

    private int companyId;

    private int adId;

}

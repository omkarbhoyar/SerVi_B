package com.example.SerVi.entity;

import com.example.SerVi.dto.ReservationDto;
import com.example.SerVi.enums.ReservationStatus;
import com.example.SerVi.enums.ReviewStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Entity
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private ReservationStatus reservationStatus;

    private ReviewStatus reviewStatus ;

    private Date bookDate;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name ="user_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;


    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name ="company_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User company;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name ="Ad_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Ad ad;


    public ReservationDto getReservatyionDto(){
        ReservationDto  dto = new ReservationDto();
        dto.setId(id);
        dto.setServiceName(ad.getServiceName());
        dto.setBookDate(bookDate);
        dto.setReservationStatus(reservationStatus);
        dto.setReviewStatus(reviewStatus);
        dto.setAdId(ad.getId());
        dto.setCompanyId(company.getId());
        dto.setUserName(user.getFirstname());
        return dto;

    }

}

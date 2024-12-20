package com.example.SerVi.repository;

import com.example.SerVi.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {


    List<Reservation> findReservationByCompanyId(int CompanyId);
    List<Reservation> findReservationByUserId(int UserId);


}

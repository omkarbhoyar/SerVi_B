package com.example.SerVi.services.client;

import com.example.SerVi.dto.AdDTO;
import com.example.SerVi.dto.AdDetailsForClientDTO;
import com.example.SerVi.dto.ReservationDto;
import com.example.SerVi.dto.ReviewDTO;

import java.util.List;

public interface ClientService {
    List<AdDTO> getAllAds();
    List<AdDTO> searchAdByName(String name);
    boolean bookService(ReservationDto reservationDto);
    AdDetailsForClientDTO getAdDetailsById(int adId);
    List<ReservationDto> getAllBookingsByUserId(int userId);
    Boolean giveReview(ReviewDTO reviewDto);

}

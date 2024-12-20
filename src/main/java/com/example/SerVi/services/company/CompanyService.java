package com.example.SerVi.services.company;

import com.example.SerVi.dto.AdDTO;
import com.example.SerVi.dto.ReservationDto;

import java.io.IOException;
import java.util.List;

public interface CompanyService {
    boolean postAd(int userId, AdDTO adDTO) throws IOException;

    List<AdDTO> getAllAds(int userId);

    AdDTO getAdById(int adId);

    boolean updateAd(int adId, AdDTO adDTO) throws IOException;

    boolean deleteAd(int adId);

    List<ReservationDto> getAllAdBookings(int companyId);
    boolean changebookingStatus(int bookingId, String status);



}

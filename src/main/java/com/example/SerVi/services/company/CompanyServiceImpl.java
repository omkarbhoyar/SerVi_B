package com.example.SerVi.services.company;

import com.example.SerVi.dto.AdDTO;
import com.example.SerVi.dto.ReservationDto;
import com.example.SerVi.entity.Ad;
import com.example.SerVi.entity.Reservation;
import com.example.SerVi.entity.User;
import com.example.SerVi.enums.ReservationStatus;
import com.example.SerVi.repository.AdRepository;
import com.example.SerVi.repository.ReservationRepository;
import com.example.SerVi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdRepository adRepository;
    @Autowired
    private ReservationRepository reservationRepository;

    public boolean postAd(int userId, AdDTO adDTO) throws IOException {
        Optional<User> OptionalUser = userRepository.findById(userId);
        if (OptionalUser.isPresent()) {
            Ad ad = new Ad();
            ad.setServiceName(adDTO.getServiceName());
            ad.setDescription(adDTO.getDescription());
            ad.setImg(adDTO.getImg().getBytes());
            ad.setPrice(adDTO.getPrice());
            ad.setUser(OptionalUser.get());

            adRepository.save(ad);
            return true;
        }
        return false;
        }


        public List<AdDTO> getAllAds(int userId){
        return adRepository.findAllByUserId(userId).stream().map(Ad::getAdDto).collect(Collectors.toList());
        }


        public AdDTO getAdById(int adId){
        Optional<Ad> ad = adRepository.findById(adId);
        if (ad.isPresent()) {
            return ad.get().getAdDto();
        }
        return null;
        }
        public boolean updateAd(int adId, AdDTO adDTO) throws IOException {
            Optional<Ad> ad = adRepository.findById(adId);
            if (ad.isPresent()) {
                Ad ad1 = ad.get();
                ad1.setServiceName(adDTO.getServiceName());
                ad1.setDescription(adDTO.getDescription());
                ad1.setPrice(adDTO.getPrice());

                if (adDTO.getImg() != null) {
                ad1.setImg(adDTO.getImg().getBytes());
                }
                adRepository.save(ad1);
                return true;
            }else {
                return false;
            }
        }



        public boolean deleteAd(int adId){
        Optional<Ad> Optipnalad = adRepository.findById(adId);
        if (Optipnalad.isPresent()) {
            adRepository.delete(Optipnalad.get());
            return true;
        } else {
            return false;
        }
        }


        public List<ReservationDto> getAllAdBookings(int companyId){
        return reservationRepository.findReservationByCompanyId(companyId).stream().map(Reservation::getReservatyionDto)
                .collect(Collectors.toList());
        }


        public boolean changebookingStatus(int bookingId, String status){

        Optional<Reservation> optionalReservation = reservationRepository.findById(bookingId);
        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            if (Objects.equals(status,"Approve")){
                reservation.setReservationStatus((ReservationStatus.APPROVED));
            }else {
                reservation.setReservationStatus((ReservationStatus.REJECTED));
            }
            reservationRepository.save(reservation);
            return true;
        }
        return false;

        }

}

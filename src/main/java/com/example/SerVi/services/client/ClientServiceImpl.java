package com.example.SerVi.services.client;

import com.example.SerVi.dto.AdDTO;
import com.example.SerVi.dto.AdDetailsForClientDTO;
import com.example.SerVi.dto.ReservationDto;
import com.example.SerVi.dto.ReviewDTO;
import com.example.SerVi.entity.Ad;
import com.example.SerVi.entity.Reservation;
import com.example.SerVi.entity.Review;
import com.example.SerVi.entity.User;
import com.example.SerVi.enums.ReservationStatus;
import com.example.SerVi.enums.ReviewStatus;
import com.example.SerVi.repository.AdRepository;
import com.example.SerVi.repository.ReservationRepository;
import com.example.SerVi.repository.ReviewRepository;
import com.example.SerVi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    public List<AdDTO>getAllAds() {
        return adRepository.findAll().stream().map(Ad::getAdDto).collect(Collectors.toList());
    }

    public List<AdDTO> searchAdByName(String name) {
        return adRepository.findAllByServiceNameContaining(name).stream().map(Ad::getAdDto).collect(Collectors.toList());
    }

    public boolean bookService(ReservationDto reservationDto) {
        Optional<Ad> ad = adRepository.findById(reservationDto.getAdId());
        Optional<User> optionalUser = userRepository.findById(reservationDto.getUserId());
        if(ad.isPresent() && optionalUser.isPresent()) {
            Reservation reservation = new Reservation();
            reservation.setBookDate(reservationDto.getBookDate());
            reservation.setReservationStatus(ReservationStatus.PENDING);
            reservation.setUser(optionalUser.get());
            reservation.setAd(ad.get());
            reservation.setCompany(ad.get().getUser());
            reservation.setReviewStatus(ReviewStatus.FALSE);
            reservationRepository.save(reservation);
            return true;

        }
        return false;

    }


    public AdDetailsForClientDTO getAdDetailsById(int adId) {
        Optional<Ad> ad = adRepository.findById(adId);
        AdDetailsForClientDTO adDetailsForClientDTO = new AdDetailsForClientDTO();
        if (ad.isPresent()) {
            adDetailsForClientDTO.setAdDTO(ad.get().getAdDto());
        }
        return adDetailsForClientDTO;

    }


    public List<ReservationDto> getAllBookingsByUserId(int userId) {
        return  reservationRepository.findReservationByUserId(userId).stream().map(Reservation::getReservatyionDto).collect(Collectors.toList());
    }


    public Boolean giveReview(ReviewDTO reviewDto) {
        Optional<User> optionalUser = userRepository.findById(reviewDto.getUserId());
        Optional<Reservation> optionalBooking = reservationRepository.findById(reviewDto.getBookId());

        if (optionalUser.isPresent() && optionalBooking.isPresent()) {
            Review review = new Review();
            review.setReviewDate(new Date());
            review.setReview(reviewDto.getReview());
            review.setRating(reviewDto.getRating());
            review.setUser(optionalUser.get());
            review.setAd(optionalBooking.get().getAd());

            reviewRepository.save(review);
            Reservation booking = optionalBooking.get();
            booking.setReviewStatus(ReviewStatus.TRUE);
            reservationRepository.save(booking);
            return true;
        }
        return false;
    }
}

package com.example.SerVi.controller;

import com.example.SerVi.dto.AdDTO;
import com.example.SerVi.dto.ReservationDto;
import com.example.SerVi.services.company.CompanyService;
import com.example.SerVi.services.company.CompanyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private CompanyServiceImpl companyServiceImpl;

    @PostMapping("/ad/{userId}")
    public ResponseEntity<?> postAd(@PathVariable int userId, @ModelAttribute AdDTO adDTO) throws IOException {
        boolean success = companyService.postAd(userId,adDTO);
        if(success) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/ads/{userId}")
    public  ResponseEntity <?> getAllAdsByUserId(@PathVariable int userId){
        return ResponseEntity.ok(companyService.getAllAds(userId));
    }

    @GetMapping("/ad/{adId}")
    public ResponseEntity<?> getAdById(@PathVariable int adId){
        AdDTO adDTO = companyService.getAdById(adId);
        if (adDTO != null) {
            return ResponseEntity.ok(adDTO);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
@PutMapping("/ad/{adId}")
    public ResponseEntity<?> updateAdById(@PathVariable int adId, @ModelAttribute AdDTO adDTO) throws IOException {
        boolean success = companyService.updateAd(adId,adDTO);
        if (success) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @DeleteMapping("/ad/{adId}")
    public ResponseEntity<?> deleteAdById(@PathVariable int adId){
        boolean success = companyService.deleteAd(adId);
        if (success) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/bookings/{companyId}")
    public ResponseEntity<List<ReservationDto>> getAllBookings(@PathVariable int companyId){
        return ResponseEntity.ok(companyService.getAllAdBookings(companyId));
    }

    @PutMapping ("/booking/{bookingId}/{status}")
    public ResponseEntity<?> changeBookings(@PathVariable int bookingId, @PathVariable String status){

        boolean success =companyService.changebookingStatus(bookingId,status);
        if(success) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }return ResponseEntity.notFound().build();
    }
}

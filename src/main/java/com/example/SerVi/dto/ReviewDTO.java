package com.example.SerVi.dto;

import com.example.SerVi.entity.Ad;
import lombok.Data;

import java.util.Date;

@Data
public class ReviewDTO {
    private int id;
    private Date reviewDate;
    private String review;
    private Long rating;
    private int userId;
    private int adId;
    private String clientName;
    private String serviceName;
    private int bookId;
}

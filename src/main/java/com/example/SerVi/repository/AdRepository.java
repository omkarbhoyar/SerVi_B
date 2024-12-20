package com.example.SerVi.repository;

import com.example.SerVi.entity.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdRepository extends JpaRepository<Ad, Integer> {

    List<Ad> findAllByUserId(int userId);

    List<Ad> findAllByServiceNameContaining(String name);

}

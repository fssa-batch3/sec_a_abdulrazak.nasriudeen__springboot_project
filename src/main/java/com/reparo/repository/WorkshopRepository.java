package com.reparo.repository;

import com.reparo.model.Workshop;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface WorkshopRepository extends JpaRepository<Workshop,Integer> {
    List<Workshop> findAll();
    Workshop findById(int workshopId);
    Workshop findByUserId(int userId);
    List<Workshop> findByCity(String city);


}


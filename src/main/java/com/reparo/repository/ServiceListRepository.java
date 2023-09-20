package com.reparo.repository;

import com.reparo.model.ServiceList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceListRepository extends JpaRepository<ServiceList,Integer> {
}

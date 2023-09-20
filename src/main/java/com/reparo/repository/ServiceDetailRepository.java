package com.reparo.repository;

import com.reparo.model.ServiceDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceDetailRepository extends JpaRepository<ServiceDetail,Integer> {
   ServiceDetail findByServiceListId(int serviceListId);
}

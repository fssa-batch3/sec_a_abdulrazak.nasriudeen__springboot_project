package com.reparo.repository;
import com.reparo.model.ServiceDetail;
import com.reparo.model.ServiceList;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
public interface ServiceListRepository extends JpaRepository<ServiceList,Integer>{
    ServiceList findByServiceId(int serviceId);

   List<ServiceList> findByServiceDetail(ServiceDetail serviceDetail);
}

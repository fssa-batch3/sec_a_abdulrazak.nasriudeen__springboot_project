package com.reparo.datamapper;

import com.reparo.dto.service.ServiceDto;
import com.reparo.model.ServiceList;

public class ServiceMapper {
    public ServiceList mapRequestToService(ServiceDto dto){
        ServiceList service =  new ServiceList();
        service.setServiceName(dto.getServiceName());
        service.setServicePrice(dto.getServicePrice());
        service.setServiceId(dto.getServiceId());
        return service;

    }
}

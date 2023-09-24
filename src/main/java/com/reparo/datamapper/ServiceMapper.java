package com.reparo.datamapper;

import com.reparo.dto.service.ServiceDto;
import com.reparo.dto.service.ServiceListResponseDto;
import com.reparo.model.ServiceDetail;
import com.reparo.model.ServiceList;

import java.util.ArrayList;
import java.util.List;

public class ServiceMapper {
    public ServiceList mapRequestToService(ServiceDto dto){
        ServiceList service =  new ServiceList();
        service.setServiceName(dto.getServiceName());
        service.setServicePrice(dto.getServicePrice());
        service.setServiceId(dto.getServiceId());
        return service;

    }
    public ServiceDto mapServiceToResponse(ServiceList service){
        ServiceDto dto =  new ServiceDto();
        dto.setServiceName(service.getServiceName());
        dto.setServicePrice(service.getServicePrice());
        dto.setServiceId(service.getServiceId());
        return dto;

    }
    public ServiceListResponseDto mapServiceListToResponse(ServiceDetail service , List<ServiceList> listOfServices){
        ServiceListResponseDto dto = new ServiceListResponseDto();
        BookingMapper bookMap =  new BookingMapper();
        dto.setListId(service.getServiceDetailId());
        dto.setServiceAmount(service.getServiceAmount());
        dto.setBookingInfo(bookMap.mapBookingToResponse(service.getBooking()));
        dto.setAccepted(service.isAcceptStatus());
        dto.setCanceled(service.isCancelStatus());
        dto.setCancelReason(service.getCancelReason());
        List<ServiceDto> listOfServiceDto =  new ArrayList<>();
        for (ServiceList ser: listOfServices) {
            listOfServiceDto.add(mapServiceToResponse(ser));
        }
        dto.setListOfServices(listOfServiceDto);
        dto.setLive(service.isLive());

        return dto;
    }

}

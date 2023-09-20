package com.reparo.service;

import com.reparo.datamapper.ServiceMapper;
import com.reparo.dto.service.ServiceDto;
import com.reparo.exception.ServiceException;
import com.reparo.exception.ValidationException;
import com.reparo.model.Booking;
import com.reparo.model.ServiceDetail;
import com.reparo.model.ServiceList;
import com.reparo.repository.BookingRepository;
import com.reparo.repository.ServiceDetailRepository;
import com.reparo.repository.ServiceListRepository;
import com.reparo.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceListService {
    @Autowired
    private ServiceDetailRepository serviceListRepository;

    @Autowired
    private ServiceListRepository serviceRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private BookingService bookingService;
    private final  Validation  validate =  new Validation() ;
    private  final ServiceMapper map =  new ServiceMapper();


    public boolean isServiceListId(int id) throws ServiceException{
        boolean exist = false;
        if(serviceListRepository!=null){
           exist = serviceListRepository.existsById(id);
           if(!exist) throw  new ServiceException("Service List is not present");
        }
        return exist;

    }
    public boolean isServiceId(int id) throws ServiceException{
        boolean exist = false;
        if(serviceRepository!=null){
            exist = serviceRepository.existsById(id);
            if(!exist) throw  new ServiceException("Service is not present");
        }
        return exist;

    }
    public int createServiceList(int bookingId) throws ServiceException{
        try {
            bookingService.isBookingExists(bookingId);
            int id = 0 ;
            if(bookingRepository!= null && serviceListRepository != null){
                Booking booking =  bookingRepository.findByBookingId(bookingId);
                booking.setOtp(0);
                bookingRepository.save(booking);
                ServiceDetail serviceList = new ServiceDetail(booking);
                ServiceDetail res =  serviceListRepository.save(serviceList);
                id = res.getServiceListId();


            }
            return id;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }
    public int createService(ServiceDto dto) throws ServiceException{
        try {
            int id = 0  ;
            ServiceList list = map.mapRequestToService(dto);
            validate.serviceCredentialValidation(list);
            isServiceListId(dto.getServiceListId());
            if(serviceListRepository!=null && serviceRepository != null){
                ServiceDetail serviceDetail =serviceListRepository.findByServiceListId(dto.getServiceListId());
                list.setServiceDetail(serviceDetail);
                ServiceList createdService = serviceRepository.save(list);
                id =  createdService.getServiceId();
            }
            return id;

        } catch (ValidationException e) {
            throw new ServiceException(e.getMessage());
        }

    }
    public boolean updateService(ServiceDto dto) throws ServiceException{
        try {
            ServiceList updated =  new ServiceList();

            isServiceId(dto.getServiceId());

            if(serviceRepository!=null)
            {
              ServiceList  exists = serviceRepository.findByServiceId(dto.getServiceId());
              exists.setServiceName(dto.getServiceName());
              exists.setServicePrice(dto.getServicePrice());
               updated = serviceRepository.save(exists);
            }
            return updated.getServiceName().equals(dto.getServiceName());
        } catch (ServiceException e) {
            throw new ServiceException(e.getMessage());
        }

    }



}

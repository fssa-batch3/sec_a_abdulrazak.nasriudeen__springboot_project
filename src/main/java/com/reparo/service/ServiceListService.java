package com.reparo.service;

import com.reparo.exception.ServiceException;
import com.reparo.model.Booking;
import com.reparo.model.ServiceDetail;
import com.reparo.repository.BookingRepository;
import com.reparo.repository.ServiceDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceListService {
    @Autowired
    private ServiceDetailRepository serviceListRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private BookingService bookingService;


    public boolean isServiceListId(int id) throws ServiceException{
        boolean exist = false;
        if(serviceListRepository!=null){
           exist = serviceListRepository.existsById(id);
           if(!exist) throw  new ServiceException("Service List is not present");
        }
        return exist;

    }
    public int createServiceList(int bookingId) throws ServiceException{
        try {
            bookingService.isBookingExists(bookingId);
            int id = 0 ;
            if(bookingRepository!= null && serviceListRepository != null){
                Booking booking =  bookingRepository.findByBookingId(bookingId);
                ServiceDetail serviceList = new ServiceDetail(booking);
                ServiceDetail res =  serviceListRepository.save(serviceList);
                id = res.getServiceListId();


            }
            return id;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }



}

package com.reparo.service;

import com.reparo.datamapper.ServiceMapper;
import com.reparo.dto.service.RejectServiceListDto;
import com.reparo.dto.service.ServiceDto;
import com.reparo.dto.service.ServiceListResponseDto;
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

import java.util.List;

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
                id = res.getServiceDetailId();


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
            Validation.serviceCredentialValidation(list);
            isServiceListId(dto.getServiceListId());
            if(serviceListRepository!=null && serviceRepository != null){
                ServiceDetail serviceDetail =serviceListRepository.findByServiceDetailId(dto.getServiceListId());
                list.setServiceDetail(serviceDetail);
                ServiceList createdService = serviceRepository.save(list);
                id =  createdService.getServiceId();
            }
            return id;

        } catch (ValidationException e) {
            throw new ServiceException(e.getMessage());
        }

    }
    public int updateService(ServiceDto dto) throws ServiceException{
        try {
            Validation.serviceCredentialValidation( map.mapRequestToService(dto));
            ServiceList updated =  new ServiceList();

            isServiceId(dto.getServiceId());

            if(serviceRepository!=null)
            {
              ServiceList  exists = serviceRepository.findByServiceId(dto.getServiceId());
              exists.setServiceName(dto.getServiceName());
              exists.setServicePrice(dto.getServicePrice());
               updated = serviceRepository.save(exists);
            }
            return updated.getServiceId();
        } catch (ServiceException | ValidationException e) {
            throw new ServiceException(e.getMessage());
        }

    }
    public void deleteService(int serviceId) throws ServiceException{
        try {
            isServiceId(serviceId);
            if (serviceRepository!=null){
                serviceRepository.deleteById(serviceId);
            }

        } catch (ServiceException e) {
            throw new ServiceException(e.getMessage());
        }
    }
    public boolean acceptServiceList(int serviceListId) throws  ServiceException{
        boolean chk = false;
        try {
            isServiceListId(serviceListId);
            if (serviceListRepository!=null){
                ServiceDetail service = serviceListRepository.findByServiceDetailId(serviceListId);
                service.setAcceptStatus(true);
                ServiceDetail changed = serviceListRepository.save(service);
                chk = changed.isAcceptStatus();

            }
            return chk;


        } catch (ServiceException e) {
            throw new ServiceException(e.getMessage());
        }
    }
    public boolean rejectServiceList(RejectServiceListDto rejectService) throws ServiceException{
        try {
            boolean chk =  false;
            isServiceListId(rejectService.getServiceListId());
            if(serviceListRepository !=null){
                ServiceDetail service = serviceListRepository.findByServiceDetailId(rejectService.getServiceListId());
                service.setAcceptStatus(false);
                service.setCancelStatus(true);
                service.setLive(false);
                service.setCancelReason(rejectService.getCancelReason());
                ServiceDetail changed = serviceListRepository.save(service);
                chk = changed.isCancelStatus();
            }
            return chk;
        } catch (ServiceException e) {
            throw new ServiceException(e.getMessage());
        }

    }
    public ServiceListResponseDto getServiceListById(int bookingId) throws ServiceException{
        try {
            ServiceListResponseDto response =  new ServiceListResponseDto();
            bookingService.isBookingExists(bookingId);
            if(serviceListRepository!=null && serviceRepository!=null && bookingRepository!=null){
                Booking booking =  bookingRepository.findByBookingId(bookingId);
                ServiceDetail service =  serviceListRepository.findByBooking(booking);
                List<ServiceList> listOfService = serviceRepository.findByServiceDetail(service);
                service.setServiceAmount(listOfService);
                ServiceDetail serviceList =  serviceListRepository.save(service);
                response = map.mapServiceListToResponse(serviceList,listOfService);
            }
            return response;


        } catch (ServiceException e) {
            throw new ServiceException(e.getMessage());
        }

    }
    public boolean makeServiceListLive(int serviceDetailId) throws ServiceException{
        try {
            isServiceListId(serviceDetailId);
            ServiceDetail detail = serviceListRepository.findByServiceDetailId(serviceDetailId);
            detail.setLive(true);
            ServiceDetail live = serviceListRepository.save(detail);
            return live.isLive();
        } catch (ServiceException e) {
            throw new ServiceException(e.getMessage());
        }

    }



}

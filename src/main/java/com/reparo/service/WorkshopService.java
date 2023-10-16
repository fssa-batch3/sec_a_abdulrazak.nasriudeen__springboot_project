package com.reparo.service;

import com.reparo.datamapper.BookingMapper;
import com.reparo.dto.booking.BookingResponseDto;
import com.reparo.dto.workshop.WorkshopDistanceResponseDto;
import com.reparo.model.Booking;
import com.reparo.repository.BookingRepository;
import com.reparo.validation.Validation;
import com.reparo.datamapper.WorkshopMapper;
import com.reparo.dto.workshop.WorkshopRequestDto;
import com.reparo.dto.workshop.WorkshopResponseDto;
import com.reparo.exception.ServiceException;
import com.reparo.exception.ValidationException;
import com.reparo.model.User;
import com.reparo.model.Workshop;
import com.reparo.repository.UserRepository;
import com.reparo.repository.WorkshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkshopService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WorkshopRepository workshopRepository;

    @Autowired
    private UserService userService;
    @Autowired
    private BookingRepository bookingRepository;
    private final WorkshopMapper map =  new WorkshopMapper();
    private final BookingMapper bookingMap =  new BookingMapper();

    public double calculateDistance(double lat1 , double lon1 , double lat2 , double lon2){
        // Radius of the Earth in kilometers
        double earthRadius = 6371;

        // Convert latitude and longitude from degrees to radians
        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);

        // Calculate the differences in latitude and longitude
        double dLat = lat2Rad - lat1Rad;
        double dLon = lon2Rad - lon1Rad;

        // Haversine formula
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return earthRadius * c;

    }


    public boolean isWorkshopExist(int id) throws ServiceException{
        boolean exist = false;
        if(workshopRepository!=null){
            exist =  workshopRepository.existsById(id);
            if(!exist) throw  new ServiceException("workshop Not present");
        }
        return exist;
    }

    public int createWorkshop(WorkshopRequestDto dto) throws ServiceException{
        int id = 0 ;
        Workshop workshop = map.mapRequestToWorkshop(dto);
        try {
            Validation.workshopValidation(workshop);
            if(userRepository!=null&&workshopRepository!=null){
                userService.isUserExist(dto.getUserId());
                User user = userRepository.findUserById(dto.getUserId());
                workshop.setUser(user);
             Workshop work = workshopRepository.save(workshop);
             id = work.getWorkShopId();
            }
        } catch (ValidationException e) {
            throw new ServiceException(e.getMessage());
        }
        return  id;
    }
    public List<WorkshopResponseDto> getAllWorkshops(int id)throws ServiceException{
        try {
            List<WorkshopResponseDto> workshops =  new ArrayList<>();
            userService.isUserExist(id);
            if(workshopRepository!=null){
                List<Workshop> workshops1 =  workshopRepository.findAll();
                for (Workshop work :workshops1) {
                    workshops.add(map.mapWorkshopToResponse(work));
                }

            }
            return workshops;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }

    }
    public List<WorkshopDistanceResponseDto> findWorkshopsNearByArea(double latitude, double longitude , String city)throws ServiceException{
        try {
            List<WorkshopDistanceResponseDto> responseDtos = new ArrayList<>();
            if(workshopRepository!=null){

                Validation.stringValidation(city,"city",25);
                String[] strArr =  city.toLowerCase().split(" ");
                List<Workshop>workshops = workshopRepository.findByCity(strArr[0]);
                if(workshops.isEmpty())throw  new ServiceException("no workshops present for this city");
                for (Workshop work:workshops) {
                    WorkshopDistanceResponseDto response = new WorkshopDistanceResponseDto();
                    response.setWorkshopInfo(map.mapWorkshopToResponse(work));
                    double distance  = calculateDistance(latitude,longitude,work.getLatitude(),work.getLongitude());
                    response.setDistance(distance);
                    responseDtos.add(response);
                }




            }
            return responseDtos;

        } catch (ValidationException e) {
            throw new ServiceException(e.getMessage());
        }


    }
    public List<BookingResponseDto> getUnAcceptedBooking(double lat,double lon,String city) throws ServiceException{
        try {
            List<BookingResponseDto> response =  new ArrayList<>();
            Validation.stringValidation(city,"city",25);
            String[]arr =  city.toLowerCase().split(" ");
            if(bookingRepository!=null){
                List<Booking> bookings =  bookingRepository.findByBookingCity(arr[0]);
                if(bookings.isEmpty()) throw new ServiceException("No Bookings Available");
                for (Booking book:bookings) {
                    if(!book.isAcceptStatus() && book.isLive()){
                        BookingResponseDto responseDto =  bookingMap.mapBookingToResponse(book);
                        double dis  = calculateDistance(lat,lon,book.getLatitude(),book.getLongitude());
                        responseDto.setDistance(dis);
                        response.add(responseDto);
                    }

                }

            }
            return response;

        } catch (ValidationException e) {
            throw new ServiceException(e.getMessage());
        }

    }

    public List<BookingResponseDto> getAllUnAcceptedBookingByWorkshopId(int id) throws ServiceException{
        try {
            List<BookingResponseDto> resp =  new ArrayList<>();
            isWorkshopExist(id);
            if(workshopRepository!=null){
                Workshop  workshop = workshopRepository.findById(id);
                resp =  getUnAcceptedBooking(workshop.getLatitude(),workshop.getLongitude(), workshop.getCity());
            }
            return resp;
        } catch (ServiceException e) {
            throw new ServiceException(e.getMessage());
        }
    }
    public WorkshopResponseDto getWorkshopByUserId(int userId ) throws ServiceException{
        try {
            WorkshopResponseDto dto = new WorkshopResponseDto();
            userService.isUserExist(userId);
            if(workshopRepository!=null){
                Workshop workshop =  workshopRepository.findByUserId(userId);
                dto =  map.mapWorkshopToResponse(workshop);


            }
            return dto;

        } catch (ServiceException e) {
            throw new ServiceException(e.getMessage());
        }

    }
    public boolean isWorkshopPresentNearByArea(String area) throws ServiceException{
        try {
            Validation.stringValidation(area,"city",35);
            List<Workshop> workshops = workshopRepository.findByCity(area);
            if(workshops.isEmpty())throw  new ServiceException("No workshops were present in "+area);
            return true;

        } catch (ValidationException e) {
            throw new ServiceException(e.getMessage());
        }
    }


}

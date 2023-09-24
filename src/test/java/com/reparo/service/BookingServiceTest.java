package com.reparo.service;

import com.reparo.dto.booking.BookingAcceptRequestDto;
import com.reparo.dto.booking.BookingRequestDto;
import com.reparo.dto.booking.BookingResponseDto;
import com.reparo.dto.workshop.WorkshopDistanceResponseDto;
import com.reparo.exception.ServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BookingServiceTest {
    @Autowired
    private BookingService service;

    @Test
    void createBookingTest(){
        try {
            BookingRequestDto request =  new BookingRequestDto();
            request.setBookedAddress("no 123 cross street");
            request.setBookedCity("chennai");
            request.setBookedState("tamilNadu");
            request.setBookedCountry("india");
            request.setBookedLatitude(45.678);
            request.setBookedLongitude(180.0);
            request.setBookedVehicleId(2);
            request.setProblem("puncture");
            if(service!= null){
                assertNotEquals(0,service.createBooking(request));
            }
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void existsBookingIdTest(){
        try {
            assertTrue(service.isBookingExists(3))  ;
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    void acceptBookingTest(){
        BookingAcceptRequestDto booking =  new BookingAcceptRequestDto();
        booking.setBookingId(2);
        booking.setWorkshopId(2);
        booking.setOtp(1234);
        try {
            assertNotNull(service.acceptBooking(booking));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void findNearByWorkshopTest(){
        try {
            List<WorkshopDistanceResponseDto> dto = service.getBookingNearWorkshops(8);
            assertFalse(dto.isEmpty());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void cancelBookingTest(){
        try {
            assertTrue(service.cancelBooking(8,"user"));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void getBookingByWorkshopId(){
        try {
           List<BookingResponseDto> booking = service.getBookingByWorkshopId(202);
           assertFalse(booking.isEmpty());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
}

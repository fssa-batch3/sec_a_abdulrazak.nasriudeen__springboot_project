package com.reparo.datamapper;

import com.reparo.dto.booking.BookingRequestDto;
import com.reparo.dto.booking.BookingResponseDto;
import com.reparo.model.Booking;

public class BookingMapper {
    private final VehicleMapper map  =  new VehicleMapper();
    private final WorkshopMapper workshopMap  = new WorkshopMapper();

    public Booking mapRequestToBooking(BookingRequestDto requestDto){
        Booking booking = new Booking();
        booking.setBookingCity(requestDto.getBookedCity().toLowerCase());
        booking.setBookingCountry(requestDto.getBookedCountry());
        booking.setBookingState(requestDto.getBookedState());
        booking.setBookingAddress(requestDto.getBookedAddress());
        booking.setLatitude(requestDto.getBookedLatitude());
        booking.setLongitude(requestDto.getBookedLongitude());
        booking.setProblem(requestDto.getProblem());
        return booking;
    }
    public BookingResponseDto mapBookingToResponse(Booking booking){
        BookingResponseDto dto =  new BookingResponseDto();
        dto.setBookingId(booking.getBookingId());
        dto.setLive(booking.isLive());
        dto.setAcceptStatus(booking.isAcceptStatus());
        dto.setRequest(booking.isRequestStatus());
        dto.setProblem(booking.getProblem());
        dto.setBookedCity(booking.getBookingCity());
        dto.setBookedState(booking.getBookingState());
        dto.setBookedCountry(booking.getBookingCountry());
        dto.setBookedAddress(booking.getBookingAddress());
        dto.setVehicleInfo(map.mapVehicleToResponse(booking.getVehicle()));
        dto.setTime(booking.getBookingTime());
        dto.setDate(booking.getBookingDate());
        if(booking.getWorkshop() !=null){
            dto.setWorkshopInfo(workshopMap.mapWorkshopToResponse(booking.getWorkshop()));
            dto.setOtp(booking.getOtp());
        }
        return dto;


    }

}

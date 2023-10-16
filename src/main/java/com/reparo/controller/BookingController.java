package com.reparo.controller;


import com.reparo.dto.ApiResponse;
import com.reparo.dto.booking.BookingAcceptRequestDto;
import com.reparo.dto.booking.BookingRequestDto;

import com.reparo.dto.booking.BookingResponseDto;
import com.reparo.dto.booking.LiveBookingRequestDto;
import com.reparo.dto.workshop.WorkshopDistanceResponseDto;
import com.reparo.exception.ServiceException;
import com.reparo.service.BookingService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:3000", "http://127.0.0.1:5501"})
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/reparo/booking/createBooking")
    public ResponseEntity<ApiResponse> createBooking(@RequestBody BookingRequestDto request){
        try {

          int id = bookingService.createBooking(request);
            ApiResponse response =  new ApiResponse(ApiResponse.SUCCESS_CODE,ApiResponse.SUCCESS);
            response.setData(Integer.toString(id));

          return ResponseEntity.ok(response);
        } catch (ServiceException e) {
            return ResponseEntity.ok(new ApiResponse(ApiResponse.FAIL_CODE,ApiResponse.FAILED,e.getMessage()));
        }
    }
    @PostMapping("/reparo/booking/acceptBooking")
    public ResponseEntity<ApiResponse> acceptBooking(@RequestBody BookingAcceptRequestDto request){
        try {
            BookingResponseDto booking = bookingService.acceptBooking(request);
            ApiResponse response =  new ApiResponse(ApiResponse.SUCCESS_CODE,ApiResponse.SUCCESS);
            JSONObject obj =  new JSONObject(booking);
            response.setData(obj.toString());
            return ResponseEntity.ok(response);
        } catch (ServiceException e) {
            return ResponseEntity.ok(new ApiResponse(ApiResponse.FAIL_CODE,ApiResponse.FAILED,e.getMessage()));
        }
    }
    @PutMapping("/reparo/booking/updateBooking")
    public  ResponseEntity<ApiResponse> updateLiveBooking(@RequestBody LiveBookingRequestDto requestDto){
        try {
            ListenableFuture<BookingResponseDto> resp  = bookingService.liveBookingTrack(requestDto);
            ApiResponse response =  new ApiResponse(ApiResponse.SUCCESS_CODE,ApiResponse.SUCCESS);
            JSONObject obj =  new JSONObject(resp.get());
            response.setData(obj.toString());
            return ResponseEntity.ok(response);
        } catch (ServiceException | ExecutionException | InterruptedException e) {
            return ResponseEntity.ok(new ApiResponse(ApiResponse.FAIL_CODE,ApiResponse.FAILED,e.getMessage()));
        }

    }

        @GetMapping("/reparo/booking/nearWorkshops")
    public ResponseEntity<ApiResponse> getNearByWorkshop(@RequestParam("bookingId")int bookingId){
        try {
            List<WorkshopDistanceResponseDto> workshops = bookingService.getBookingNearWorkshops(bookingId);
            ApiResponse response =  new ApiResponse(ApiResponse.SUCCESS_CODE,ApiResponse.SUCCESS);
            JSONArray arr =  new JSONArray(workshops);
            response.setData(arr.toString());
            return ResponseEntity.ok(response);
        } catch (ServiceException e) {
            return ResponseEntity.ok(new ApiResponse(ApiResponse.FAIL_CODE,ApiResponse.FAILED,e.getMessage()));
        }
    }

    @GetMapping("/reparo/booking/getBookingById")
    public ResponseEntity<ApiResponse> getBookingById(@RequestParam("bookingId") int bookingId ){
        try {
            BookingResponseDto  booking  = bookingService.getBookingById(bookingId);
            JSONObject obj =  new JSONObject(booking);
            ApiResponse response =  new ApiResponse(ApiResponse.SUCCESS_CODE,ApiResponse.SUCCESS);
            response.setData(obj.toString());
            return ResponseEntity.ok(response);
        } catch (ServiceException e) {
            return ResponseEntity.ok(new ApiResponse(ApiResponse.FAIL_CODE,ApiResponse.FAILED,e.getMessage()));
        }
    }
    @GetMapping("/reparo/booking/cancelBooking")
    public ResponseEntity<ApiResponse> cancelBooking(@RequestParam int bookingId , @RequestParam String user){
        try {
            bookingService.cancelBooking(bookingId,user);
            ApiResponse response =  new ApiResponse(ApiResponse.SUCCESS_CODE,ApiResponse.SUCCESS);
           response.setData("cancelled");
            return ResponseEntity.ok(response);
        } catch (ServiceException e) {
            return ResponseEntity.ok(new ApiResponse(ApiResponse.FAIL_CODE,ApiResponse.FAILED,e.getMessage()));
        }
    }

    @GetMapping("/reparo/booking/getBookingsByWorkshopId")
    public ResponseEntity<ApiResponse> getBookingsByWorkshopId(@RequestParam int workshopId){
        try {
            List<BookingResponseDto> bookings =  bookingService.getBookingByWorkshopId(workshopId);
            JSONArray arr =  new JSONArray(bookings);
            ApiResponse response =  new ApiResponse(ApiResponse.SUCCESS_CODE,ApiResponse.SUCCESS);
            response.setData(arr.toString());
            return ResponseEntity.ok(response);
        } catch (ServiceException e) {
            return ResponseEntity.ok(new ApiResponse(ApiResponse.FAIL_CODE,ApiResponse.FAILED,e.getMessage()));

        }

    }


}

package com.reparo.controller;
import com.reparo.dto.ApiResponse;
import com.reparo.dto.booking.BookingResponseDto;
import com.reparo.dto.workshop.WorkshopRequestDto;
import com.reparo.dto.workshop.WorkshopResponseDto;
import com.reparo.exception.ServiceException;
import com.reparo.service.WorkshopService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:3000", "http://127.0.0.1:5501"})
public class WorkshopController {
@Autowired
private  WorkshopService workshopService;


    @PostMapping("/reparo/workshop/createWorkshop")
    public ResponseEntity<ApiResponse>createWorkShop(@RequestBody WorkshopRequestDto request,@RequestParam int userId){
        request.setUserId(userId);
        try {
          int id =  workshopService.createWorkshop(request);
            ApiResponse response =  new ApiResponse(ApiResponse.SUCCESS_CODE,ApiResponse.SUCCESS);
            response.setData(Integer.toString(id));
            return ResponseEntity.ok(response);

        } catch (ServiceException e) {
            return ResponseEntity.ok(new ApiResponse(ApiResponse.FAIL_CODE,ApiResponse.FAILED,e.getMessage()));

        }

    }
    @GetMapping("/reparo/workshop/getAllWorkshops")
    public ResponseEntity<ApiResponse> getAllWorkshops(@RequestParam int id){
        try {
         List<WorkshopResponseDto> workshops  =  workshopService.getAllWorkshops(id);
            ApiResponse response =  new ApiResponse(ApiResponse.SUCCESS_CODE,ApiResponse.SUCCESS);
            JSONArray arr =  new JSONArray(workshops);
            response.setData(arr.toString());
         return ResponseEntity.ok(response);
        } catch (ServiceException e) {
            return ResponseEntity.ok(new ApiResponse(ApiResponse.FAIL_CODE,ApiResponse.FAILED,e.getMessage()));
        }
    }
    @GetMapping("/reparo/workshop/getWorkshopByUserId")
        public ResponseEntity<ApiResponse> getWorkshopByUserId(@RequestParam int userId){
        try {
            WorkshopResponseDto dto =  workshopService.getWorkshopByUserId(userId);
            ApiResponse response =  new ApiResponse(ApiResponse.SUCCESS_CODE,ApiResponse.SUCCESS);
            JSONObject obj =  new JSONObject(dto);
            response.setData(obj.toString());
            return ResponseEntity.ok(response);
        } catch (ServiceException e) {
            return ResponseEntity.ok(new ApiResponse(ApiResponse.FAIL_CODE,ApiResponse.FAILED,e.getMessage()));
        }
        }


    @GetMapping("/reparo/workshop/getAllUnAcceptedBooking")
    public ResponseEntity<ApiResponse> getAllUnAcceptedBooking(@RequestParam int workshopId){
        try {
            List<BookingResponseDto> bookings  =  workshopService.getAllUnAcceptedBookingByWorkshopId(workshopId);
            ApiResponse response =  new ApiResponse(ApiResponse.SUCCESS_CODE,ApiResponse.SUCCESS);
            JSONArray arr =  new JSONArray(bookings);
            response.setData(arr.toString());
            return ResponseEntity.ok(response);
        } catch (ServiceException e) {
            return ResponseEntity.ok(new ApiResponse(ApiResponse.FAIL_CODE,ApiResponse.FAILED,e.getMessage()));
        }
    }
    @GetMapping("/reparo/workshop/isWorkshopPresent")
    public ResponseEntity<ApiResponse> isWorkshopPresent(@RequestParam String area){
        try {
            workshopService.isWorkshopPresentNearByArea(area);
            ApiResponse response =  new ApiResponse(ApiResponse.SUCCESS_CODE,ApiResponse.SUCCESS);
            return  ResponseEntity.ok(response);
        } catch (ServiceException e) {
            return ResponseEntity.ok(new ApiResponse(ApiResponse.FAIL_CODE,ApiResponse.FAILED,e.getMessage()));
        }
    }






}

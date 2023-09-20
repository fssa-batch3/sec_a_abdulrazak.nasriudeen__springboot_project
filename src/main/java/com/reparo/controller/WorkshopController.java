package com.reparo.controller;
import com.reparo.dto.booking.BookingResponseDto;
import com.reparo.dto.workshop.WorkshopRequestDto;
import com.reparo.dto.workshop.WorkshopResponseDto;
import com.reparo.exception.ServiceException;
import com.reparo.service.WorkshopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workshop")
@CrossOrigin(origins = "http://127.0.0.1:5501")
public class WorkshopController {
@Autowired
private  WorkshopService workshopService;


    @PostMapping("/createWorkshop")
    public ResponseEntity<String>createWorkShop(@RequestBody WorkshopRequestDto request,@RequestParam int userId){
        request.setUserId(userId);
        try {
          int id =  workshopService.createWorkshop(request);
            return ResponseEntity.ok(Integer.toString(id));

        } catch (ServiceException e) {
            return ResponseEntity.ok(e.getMessage());

        }

    }
    @GetMapping("/getAllWorkshops")
    public ResponseEntity<List<WorkshopResponseDto>> getAllWorkshops(@RequestParam int id){
        try {
         List<WorkshopResponseDto> workshops  =  workshopService.getAllWorkshops(id);
         return ResponseEntity.ok(workshops); 
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("getWorkshopByUserId")
        public ResponseEntity<WorkshopResponseDto> getWorkshopByUserId(@RequestParam int userId){
        try {
            WorkshopResponseDto dto =  workshopService.getWorkshopByUserId(userId);
            return ResponseEntity.ok(dto);
        } catch (ServiceException e) {
            return ResponseEntity.noContent().build();
        }

    }
    @GetMapping("/getAllUnAcceptedBooking")
    public ResponseEntity<List<BookingResponseDto>> getAllUnAcceptedBooking(@RequestParam int workshopId){
        try {
            List<BookingResponseDto> bookings  =  workshopService.getAllUnAcceptedBookingByWorkshopId(workshopId);
            return ResponseEntity.ok(bookings);
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }






}

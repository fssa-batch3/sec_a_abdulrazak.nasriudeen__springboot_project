package com.reparo.controller;

import com.reparo.dto.service.ServiceDto;
import com.reparo.dto.service.ServiceListResponseDto;
import com.reparo.exception.ServiceException;
import com.reparo.service.ServiceListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("service")
@CrossOrigin(origins = "http://127.0.0.1:5501")
public class ServiceController {
    @Autowired
    private ServiceListService listService;

    @GetMapping("/createServiceList")
    public ResponseEntity<String> createServiceList(@RequestParam  int bookingId ) {
        try {
           int id = listService.createServiceList(bookingId);
           return ResponseEntity.ok(Integer.toString(id));
        } catch (ServiceException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/createService")
    public ResponseEntity<String> createService(@RequestBody ServiceDto service){
        try {
            int id = listService.createService(service);
            return ResponseEntity.ok(Integer.toString(id));
        } catch (ServiceException e) {
            return ResponseEntity.notFound().build();

        }


    }
    @GetMapping("getServiceListByBookingId")
    public ResponseEntity<ServiceListResponseDto> getServiceById(@RequestParam int bookingId){
        try {
            ServiceListResponseDto service = listService.getServiceListById(bookingId);
            return ResponseEntity.ok(service);
        } catch (ServiceException e) {
            return ResponseEntity.notFound().build();
        }

    }
    @PostMapping("/updateService")
    public ResponseEntity<String> updateService(@RequestBody ServiceDto service){
        try {
            int id =  listService.updateService(service);
            return ResponseEntity.ok(Integer.toString(id));
        } catch (ServiceException e) {
            return ResponseEntity.notFound().build();
        }

    }
    @GetMapping("/deleteService")
    public ResponseEntity<String> deleteService(@RequestParam int serviceId){
        try {
           listService.deleteService(serviceId);
          return ResponseEntity.ok("true");
        } catch (ServiceException e) {
            return ResponseEntity.ok("false");

        }

    }
    @GetMapping("makeServiceLive")
    public ResponseEntity<String> makeLive(@RequestParam int serviceDetailId){

        try {
            listService.makeServiceListLive(serviceDetailId);
          return   ResponseEntity.ok("true");
        } catch (ServiceException e) {
           return ResponseEntity.ok(e.getMessage());

        }
    }
}

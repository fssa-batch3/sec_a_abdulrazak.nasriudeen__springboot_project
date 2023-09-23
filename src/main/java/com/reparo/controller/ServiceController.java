package com.reparo.controller;

import com.reparo.dto.ApiResponse;
import com.reparo.dto.service.ServiceDto;
import com.reparo.dto.service.ServiceListResponseDto;
import com.reparo.exception.ServiceException;
import com.reparo.service.ServiceListService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://127.0.0.1:5501")
public class ServiceController {
    @Autowired
    private ServiceListService listService;

    @GetMapping("/reparo/service/createServiceList")
    public ResponseEntity<ApiResponse> createServiceList(@RequestParam  int bookingId ) {
        try {
           int id = listService.createServiceList(bookingId);
           ApiResponse response =  new ApiResponse(200,"success");
           response.setData(Integer.toString(id));
           return ResponseEntity.ok(response);
        } catch (ServiceException e) {
            return ResponseEntity.ok(new ApiResponse(400,"failure",e.getMessage()));
        }
    }

    @PostMapping("/reparo/service/createService")
    public ResponseEntity<ApiResponse> createService(@RequestBody ServiceDto service){
        try {
            int id = listService.createService(service);
            ApiResponse response =  new ApiResponse(200,"success");
            response.setData(Integer.toString(id));
            return ResponseEntity.ok(response);
        } catch (ServiceException e) {
            return ResponseEntity.ok(new ApiResponse(400,"failure",e.getMessage()));

        }


    }
    @GetMapping("/reparo/service/getServiceListByBookingId")
    public ResponseEntity<ApiResponse> getServiceById(@RequestParam int bookingId){
        try {
            ServiceListResponseDto service = listService.getServiceListById(bookingId);
            ApiResponse response =  new ApiResponse(200,"success");
            JSONObject obj =  new JSONObject(service);
            response.setData(obj.toString());
            return ResponseEntity.ok(response);
        } catch (ServiceException e) {
            return ResponseEntity.ok(new ApiResponse(400,"failure",e.getMessage()));
        }

    }
    @PostMapping("/reparo/service/updateService")
    public ResponseEntity<ApiResponse> updateService(@RequestBody ServiceDto service){
        try {
            int id =  listService.updateService(service);
            ApiResponse response =  new ApiResponse(200,"success");
            response.setData(Integer.toString(id));
            return ResponseEntity.ok(response);
        } catch (ServiceException e) {
            return ResponseEntity.ok(new ApiResponse(400,"failure",e.getMessage()));
        }

    }
    @GetMapping("/reparo/service/deleteService")
    public ResponseEntity<ApiResponse> deleteService(@RequestParam int serviceId){
        try {
           listService.deleteService(serviceId);
          return ResponseEntity.ok(new ApiResponse(200,"success"));
        } catch (ServiceException e) {
            return ResponseEntity.ok(new ApiResponse(400,"failure",e.getMessage()));

        }

    }
    @GetMapping("/reparo/service/makeServiceLive")
    public ResponseEntity<ApiResponse> makeLive(@RequestParam int serviceDetailId){

        try {
            listService.makeServiceListLive(serviceDetailId);
            return ResponseEntity.ok(new ApiResponse(200,"success"));
        } catch (ServiceException e) {
            return ResponseEntity.ok(new ApiResponse(400,"failure",e.getMessage()));

        }
    }
}

package com.reparo.controller;

import com.reparo.dto.ApiResponse;
import com.reparo.dto.vehicle.VehicleRequestDto;
import com.reparo.exception.ServiceException;
import com.reparo.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://127.0.0.1:5501")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/reparo/vehicle/createVehicle")
    public ResponseEntity<ApiResponse> createVehicle(@RequestBody VehicleRequestDto requestDto){
        try {
            int id =  vehicleService.addVehicle(requestDto);
            ApiResponse response  =  new ApiResponse(200,"success");
            response.setData(Integer.toString(id));
            return ResponseEntity.ok(response);
        } catch (ServiceException e) {
            return ResponseEntity.ok(new ApiResponse(400,"failure",e.getMessage()));
        }
    }
}


package com.reparo.controller;

import com.reparo.dto.vehicle.VehicleRequestDto;
import com.reparo.exception.ServiceException;
import com.reparo.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicle")
@CrossOrigin(origins = "http://127.0.0.1:5501")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/createVehicle")
    public ResponseEntity<String> createVehicle(@RequestBody VehicleRequestDto requestDto){
        try {
            int id =  vehicleService.addVehicle(requestDto);
            return ResponseEntity.ok(Integer.toString(id));
        } catch (ServiceException e) {
            return ResponseEntity.ok(e.getMessage());
        }

    }
}


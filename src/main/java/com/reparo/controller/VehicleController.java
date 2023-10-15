package com.reparo.controller;

import com.reparo.dto.ApiResponse;
import com.reparo.dto.vehicle.VehicleRequestDto;
import com.reparo.dto.vehicle.VehicleResponseDto;
import com.reparo.exception.ServiceException;
import com.reparo.service.VehicleService;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:3000", "http://127.0.0.1:5501"})
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/reparo/vehicle/createVehicle")
    public ResponseEntity<ApiResponse> createVehicle(@RequestBody VehicleRequestDto requestDto){
        try {
            int id =  vehicleService.addVehicle(requestDto);
            ApiResponse response =  new ApiResponse(ApiResponse.SUCCESS_CODE,ApiResponse.SUCCESS);
            response.setData(Integer.toString(id));
            return ResponseEntity.ok(response);
        } catch (ServiceException e) {
            return ResponseEntity.ok(new ApiResponse(ApiResponse.FAIL_CODE,ApiResponse.FAILED,e.getMessage()));
        }
    }
    @GetMapping("/reparo/vehicle/findVehicleByUserId")
    public ResponseEntity<ApiResponse>findVehiclesByUserId(@RequestParam int userId){
        try {
            List<VehicleResponseDto> vehicles = vehicleService.findVehiclesByUserId(userId);
            ApiResponse response =  new ApiResponse(ApiResponse.SUCCESS_CODE,ApiResponse.SUCCESS);
            JSONArray arr = new JSONArray(vehicles);
            response.setData(arr.toString());
            return ResponseEntity.ok(response);
        } catch (ServiceException e) {
            return ResponseEntity.ok(new ApiResponse(ApiResponse.FAIL_CODE,ApiResponse.FAILED,e.getMessage()));
        }
    }
}


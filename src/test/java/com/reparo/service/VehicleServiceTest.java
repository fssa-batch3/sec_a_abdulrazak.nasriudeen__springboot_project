package com.reparo.service;

import com.reparo.dto.vehicle.VehicleRequestDto;
import com.reparo.dto.vehicle.VehicleResponseDto;
import com.reparo.exception.ServiceException;
import com.reparo.repository.VehicleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
 class VehicleServiceTest {
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired

    private  VehicleService  vehicleService;
    @Test
    void createVehicleTest(){
        try {
            if(vehicleService!=null){
                VehicleRequestDto dto = new VehicleRequestDto();
                dto.setVehicleCompany("hero");
                dto.setVehicleModel("splendor");
                dto.setVehicleNumber("TN09AB1234");
                dto.setVehicleType(2);
                dto.setUserId(953);
                dto.setVehicleYear(2022);
                Assertions.assertNotEquals( 0 ,vehicleService.addVehicle(dto));

            }

        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void vehicleExistTest(){
        try {
            if (vehicleService!=null) {
                Assertions.assertTrue(vehicleService.isVehicleExist(2));
            }
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void getVehicleByIdTest()
    {
        if (vehicleService!=null) {
            try {
                Assertions.assertNotNull(vehicleService.findVehicleById(2));
            } catch (ServiceException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @Test
    void getVehicleByUserId(){
        try {
            List<VehicleResponseDto> res = vehicleService.findVehiclesByUserId(953);
            assertFalse(res.isEmpty());
            System.out.println((res.size()));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }



}

package com.reparo.datamapper;

import com.reparo.dto.vehicle.VehicleRequestDto;
import com.reparo.dto.vehicle.VehicleResponseDto;
import com.reparo.model.User;
import com.reparo.model.Vehicle;
public class VehicleMapper {

    public Vehicle mapRequestToVehicle(VehicleRequestDto dto){
        return new Vehicle(dto.getVehicleType(), dto.getVehicleNumber(), dto.getVehicleYear(), dto.getVehicleCompany(), dto.getVehicleModel());

    }
    public VehicleResponseDto mapVehicleToResponse(Vehicle vehicle){
         UserMapper mapper = new UserMapper();
         VehicleResponseDto responseDto =  new VehicleResponseDto();
         User user = vehicle.getUser();
         responseDto.setUserInfo(mapper.mapUserToResponse(user));
         responseDto.setVehicleNumber(vehicle.getVehicleNumber());
         responseDto.setVehicleType(vehicle.getType());
         responseDto.setVehicleYear(vehicle.getYear());
         responseDto.setVehicleCompany(vehicle.getCompany());
         responseDto.setVehicleModel(vehicle.getModel());
         responseDto.setVehicleId(vehicle.getVehicleId());
        return responseDto;
    }
}

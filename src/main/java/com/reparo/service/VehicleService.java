package com.reparo.service;

import com.reparo.datamapper.VehicleMapper;
import com.reparo.dto.vehicle.VehicleRequestDto;
import com.reparo.dto.vehicle.VehicleResponseDto;
import com.reparo.exception.ServiceException;
import com.reparo.exception.ValidationException;
import com.reparo.model.User;
import com.reparo.model.Vehicle;
import com.reparo.repository.UserRepository;
import com.reparo.repository.VehicleRepository;
import com.reparo.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService service;
    private final VehicleMapper map = new VehicleMapper();


    public boolean isVehicleExist(int id) throws ServiceException{
        boolean exist = false;
        if(vehicleRepository!=null){
             exist =  vehicleRepository.existsById(id);
            if(!exist) throw  new ServiceException("Vehicle Not present");
        }
        return exist;
    }
    public int addVehicle(VehicleRequestDto dto) throws ServiceException{
        try {
            Vehicle vehicle1 =  new Vehicle();
            if(vehicleRepository!=null && service !=null && userRepository!=null){
                service.isUserExist(dto.getUserId());
                Vehicle existVehicle = vehicleRepository.findByVehicleNumber(dto.getVehicleNumber());
                if(existVehicle!=null) throw new ServiceException("you already registered your vehicle");
                User user =  userRepository.findUserById(dto.getUserId());
                Vehicle vehicle = map.mapRequestToVehicle(dto);
                Validation  .vehicleCredentialValidation(vehicle);

                vehicle.setUser(user);
                vehicle1 = vehicleRepository.save(vehicle);
            }

            return vehicle1.getVehicleId();
        } catch (ServiceException | ValidationException e) {
            throw new ServiceException(e.getMessage());
        }
    }
    public VehicleResponseDto findVehicleById(int vehicleId)throws ServiceException{
        try {
            VehicleResponseDto dto =  new VehicleResponseDto();
            isVehicleExist(vehicleId);
            if(vehicleRepository!=null){
                Vehicle vehicle = vehicleRepository.findByVehicleId(vehicleId);
                dto =  map.mapVehicleToResponse(vehicle);
            }

            return dto;

        } catch (ServiceException e) {
            throw new ServiceException(e.getMessage());
        }

    }

    public List<VehicleResponseDto> findVehiclesByUserId(int userId) throws ServiceException{
        try {
            List<VehicleResponseDto> responses =  new ArrayList<>();
            service.isUserExist(userId);
            if(userRepository!=null&&vehicleRepository!=null){
                User user =  userRepository.findUserById(userId);
                List<Vehicle> vehicles =  vehicleRepository.findByUser(user);
                if(vehicles.isEmpty()) throw new ServiceException("No vehicles Were Present");
                for (Vehicle vehicle: vehicles) {
                    responses.add(map.mapVehicleToResponse(vehicle));
                }
             }

            return responses;
        } catch (ServiceException e) {
            throw new ServiceException(e.getMessage());
        }
    }

}

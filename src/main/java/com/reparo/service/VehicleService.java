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
import org.springframework.stereotype.Service;


@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService service;
    private final VehicleMapper map = new VehicleMapper();
    private final Validation validate =  new Validation();

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
                User user =  userRepository.findUserById(dto.getUserId());
                Vehicle vehicle = map.mapRequestToVehicle(dto);
                validate.vehicleCredentialValidation(vehicle);
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
}

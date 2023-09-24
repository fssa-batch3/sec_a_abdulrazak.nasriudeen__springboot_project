package com.reparo.repository;

import com.reparo.model.User;
import com.reparo.model.Vehicle;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface VehicleRepository extends JpaRepository<Vehicle , Integer> {
    Vehicle findByVehicleId(int vehicleId);
    Vehicle findByVehicleNumber(String vehicleNumber);
    List<Vehicle> findByUser(User user);


}

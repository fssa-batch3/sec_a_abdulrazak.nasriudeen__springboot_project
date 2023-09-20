package com.reparo.dto.vehicle;

import com.reparo.dto.user.UserResponseDto;

public class VehicleResponseDto extends VehicleRequestDto{
    private int vehicleId;
    private UserResponseDto userInfo;

    public UserResponseDto getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserResponseDto userInfo) {
        this.userInfo = userInfo;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }
}

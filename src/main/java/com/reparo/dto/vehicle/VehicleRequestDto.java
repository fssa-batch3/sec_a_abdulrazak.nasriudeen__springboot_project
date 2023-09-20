package com.reparo.dto.vehicle;



public class VehicleRequestDto {

    private int userId;
    private int vehicleType;
    private String vehicleNumber;
    private int vehicleYear;
    private String vehicleCompany;
    private String vehicleModel;
    public VehicleRequestDto() {
    }

    // Constructor with parameters
    public VehicleRequestDto(int userId, int type, String vehicleNumber, int year, String company, String model) {
        this.userId = userId;
        this.vehicleType = type;
        this.vehicleNumber = vehicleNumber;
        this.vehicleYear = year;
        this.vehicleCompany = company;
        this.vehicleModel = model;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(int type) {
        this.vehicleType = type;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public int getVehicleYear() {
        return vehicleYear;
    }

    public void setVehicleYear(int year) {
        this.vehicleYear = year;
    }

    public String getVehicleCompany() {
        return vehicleCompany;
    }

    public void setVehicleCompany(String company) {
        this.vehicleCompany = company;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String model) {
        this.vehicleModel = model;
    }

}

package com.reparo.dto.workshop;

import com.reparo.dto.user.UserResponseDto;

public class WorkshopResponseDto {

    private int userId;
    private UserResponseDto user;
    private int workshopId;
    private String workshopName;
    private String workshopImage;
    private String workshopCity;
    private String workshopState;
    private String workshopCountry;
    private String workshopAddress;

    private int workshopType;
    private String workshopOpenTime;
    private String workshopCloseTime;
    private int generalServicePrice;
    private int electricalServicePrice;
    private int suspensionServicePrice;
    private int engineServicePrice;

    // Constructor
    public WorkshopResponseDto() {
        // Default constructor
    }

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getWorkshopId() {
        return workshopId;
    }

    public void setWorkshopId(int workshopId) {
        this.workshopId = workshopId;
    }

    public String getWorkshopName() {
        return workshopName;
    }

    public void setWorkshopName(String workshopName) {
        this.workshopName = workshopName;
    }

    public String getWorkshopImage() {
        return workshopImage;
    }

    public void setWorkshopImage(String image) {
        this.workshopImage = image;
    }

    public String getWorkshopCity() {
        return workshopCity;
    }

    public void setWorkshopCity(String city) {
        this.workshopCity = city;
    }

    public String getWorkshopState() {
        return workshopState;
    }

    public void setWorkshopState(String state) {
        this.workshopState = state;
    }

    public String getWorkshopCountry() {
        return workshopCountry;
    }

    public void setWorkshopCountry(String country) {
        this.workshopCountry = country;
    }

    public String getWorkshopAddress() {
        return workshopAddress;
    }

    public void setWorkshopAddress(String address) {
        this.workshopAddress = address;
    }



    public int getWorkshopType() {
        return workshopType;
    }

    public void setWorkshopType(int type) {
        this.workshopType = type;
    }

    public String getWorkshopOpenTime() {
        return workshopOpenTime;
    }

    public void setWorkshopOpenTime(String openTime) {
        this.workshopOpenTime = openTime;
    }

    public String getWorkshopCloseTime() {
        return workshopCloseTime;
    }

    public void setWorkshopCloseTime(String closeTime) {
        this.workshopCloseTime = closeTime;
    }

    public int getGeneralServicePrice() {
        return generalServicePrice;
    }

    public void setGeneralServicePrice(int generalPrice) {
        this.generalServicePrice = generalPrice;
    }

    public int getElectricalServicePrice() {
        return electricalServicePrice;
    }

    public void setElectricalServicePrice(int electricalPrice) {
        this.electricalServicePrice = electricalPrice;
    }

    public int getSuspensionServicePrice() {
        return suspensionServicePrice;
    }

    public void setSuspensionServicePrice(int suspensionPrice) {
        this.suspensionServicePrice = suspensionPrice;
    }

    public int getEngineServicePrice() {
        return engineServicePrice;
    }

    public void setEngineServicePrice(int enginePrice) {
        this.engineServicePrice = enginePrice;
    }

    public UserResponseDto getUser() {
        return user;
    }

    public void setUser(UserResponseDto user) {
        this.user = user;
    }
}

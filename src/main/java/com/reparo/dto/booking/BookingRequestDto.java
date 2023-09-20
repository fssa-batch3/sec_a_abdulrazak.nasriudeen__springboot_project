package com.reparo.dto.booking;

public class BookingRequestDto {
    private int bookedVehicleId;
    private double bookedLatitude;
    private double bookedLongitude;
    private String  bookedCity;
    private String bookedState;
    private String bookedAddress;
    private String bookedCountry;
    private String problem;

    public String getBookedAddress() {
        return bookedAddress;
    }

    public String getBookedCity() {
        return bookedCity;
    }

    public double  getBookedLatitude() {
        return bookedLatitude;
    }

    public double  getBookedLongitude() {
        return bookedLongitude;
    }

    public void setBookedLongitude(double bookedLongitude) {
        this.bookedLongitude = bookedLongitude;
    }

    public void setBookedLatitude(double bookedLatitude) {
        this.bookedLatitude = bookedLatitude;
    }

    public String getBookedCountry() {
        return bookedCountry;
    }

    public String getBookedState() {
        return bookedState;
    }

    public void setBookedState(String bookedState) {
        this.bookedState = bookedState;
    }

    public void setBookedCountry(String bookedCountry) {
        this.bookedCountry = bookedCountry;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public int getBookedVehicleId() {
        return bookedVehicleId;
    }

    public void setBookedVehicleId(int bookedVehicleId) {
        this.bookedVehicleId = bookedVehicleId;
    }

    public void setBookedCity(String bookedCity) {
        this.bookedCity = bookedCity;
    }

    public void setBookedAddress(String bookedAddress) {
        this.bookedAddress = bookedAddress;
    }
}

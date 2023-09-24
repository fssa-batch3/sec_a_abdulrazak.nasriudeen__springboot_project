package com.reparo.dto.booking;

import com.reparo.dto.vehicle.VehicleResponseDto;
import com.reparo.dto.workshop.WorkshopResponseDto;

public class BookingResponseDto extends BookingRequestDto{
    private int bookingId;
    private boolean isLive;
    private boolean acceptStatus;
    private WorkshopResponseDto workshopInfo;
    private VehicleResponseDto vehicleInfo;
    private double distance ;
    private String time;
    private String date;
    private int otp;
    private boolean request;

    public int getOtp() {
        return otp;
    }

    public boolean isRequest() {
        return request;
    }

    public void setRequest(boolean request) {
        this.request = request;
    }

    public void setOtp(int otp) {
        this.otp = otp;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isAcceptStatus() {
        return acceptStatus;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public boolean isLive() {
        return isLive;
    }

    public WorkshopResponseDto getWorkshopInfo() {
        return workshopInfo;
    }

    public VehicleResponseDto getVehicleInfo() {
        return vehicleInfo;
    }

    public void setVehicleInfo(VehicleResponseDto vehicleInfo) {
        this.vehicleInfo = vehicleInfo;
    }

    public void setWorkshopInfo(WorkshopResponseDto workshopInfo) {
        this.workshopInfo = workshopInfo;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public void setAcceptStatus(boolean acceptStatus) {
        this.acceptStatus = acceptStatus;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}

package com.reparo.dto.service;

import com.reparo.dto.booking.BookingResponseDto;

import java.util.List;

public class ServiceListResponseDto {
    private int listId;
    private  int bookingId;

    private boolean isAccepted;
    private boolean isCanceled;

    private int serviceAmount;

    private String cancelReason;

    private BookingResponseDto bookingInfo;

    private List<ServiceDto> listOfServices;
    private boolean live;

    public BookingResponseDto getBookingInfo() {
        return bookingInfo;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public void setBookingInfo(BookingResponseDto bookingInfo) {
        this.bookingInfo = bookingInfo;
    }

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public void setCanceled(boolean canceled) {
        isCanceled = canceled;
    }

    public int getServiceAmount() {
        return serviceAmount;
    }

    public void setServiceAmount(int serviceAmount) {
        this.serviceAmount = serviceAmount;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public List<ServiceDto> getListOfServices() {
        return listOfServices;
    }

    public void setListOfServices(List<ServiceDto> listOfServices) {
        this.listOfServices = listOfServices;
    }







}

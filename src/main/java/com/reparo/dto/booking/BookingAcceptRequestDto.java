package com.reparo.dto.booking;

public class BookingAcceptRequestDto {
    private int workshopId;
    private int bookingId;
    private int otp;

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getWorkshopId() {
        return workshopId;
    }

    public int getOtp() {
        return otp;
    }

    public void setOtp(int otp) {
        this.otp = otp;
    }

    public void setWorkshopId(int workshopId) {
        this.workshopId = workshopId;
    }
}

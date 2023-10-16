package com.reparo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "booking")
public class Booking {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "booking_id")
  private int bookingId;

  @ManyToOne
  @JoinColumn(name = "vehicle_id", nullable = false)
  private Vehicle vehicle;

  @ManyToOne
  @JoinColumn(name = "workshop_id")
  private Workshop workshop;

  @Column(name = "latitude", nullable = false)
  private double latitude;

  @Column(name = "longitude", nullable = false)
  private double longitude;

  @Column(name = "booking_city", nullable = false)
  private String bookingCity;

  @Column(name = "booking_state", nullable = false)
  private String bookingState;

  @Column(name = "booking_country", nullable = false)
  private String bookingCountry;

  @Column(name = "booking_address", nullable = false)
  private String bookingAddress;

  @Column(name = "request_status")
  private boolean requestStatus;

  @Column(name = "accept_status")
  private boolean acceptStatus;

  @Column(name = "problem", nullable = false)
  private String problem;

  @Column(name = "booking_date")
  private String bookingDate;

  @Column(name = "booking_time")
  private String bookingTime;

  @Column(name = "is_live")
  private boolean isLive;

  @Column(name = "is_read")
  private Boolean isRead;

  @Column(name = "otp")
  private int otp;

  @Column(name = "mec_latitude")
  private double mecLatitude;
  @Column(name = "mec_longitude")
  private double mecLongitude;



  public int getBookingId() {
    return bookingId;
  }

  public Boolean getRead() {
    return isRead;
  }

  public int getOtp() {
    return otp;
  }

  public void setOtp(int otp) {
    this.otp = otp;
  }

  public void setRead(Boolean read) {
    isRead = read;
  }

  public void setBookingId(int bookingId) {
    this.bookingId = bookingId;
  }

  public Vehicle getVehicle() {
    return vehicle;
  }

  public void setVehicle(Vehicle vehicle) {
    this.vehicle = vehicle;
  }

  public Workshop getWorkshop() {
    return workshop;
  }

  public void setWorkshop(Workshop workshop) {
    this.workshop = workshop;
  }

  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  public String getBookingCity() {
    return bookingCity;
  }

  public void setBookingCity(String bookingCity) {
    this.bookingCity = bookingCity;
  }

  public String getBookingState() {
    return bookingState;
  }

  public void setBookingState(String bookingState) {
    this.bookingState = bookingState;
  }

  public String getBookingCountry() {
    return bookingCountry;
  }

  public void setBookingCountry(String bookingCountry) {
    this.bookingCountry = bookingCountry;
  }

  public String getBookingAddress() {
    return bookingAddress;
  }

  public void setBookingAddress(String bookingAddress) {
    this.bookingAddress = bookingAddress;
  }

  public boolean isRequestStatus() {
    return requestStatus;
  }

  public void setRequestStatus(boolean requestStatus) {
    this.requestStatus = requestStatus;
  }

  public boolean isAcceptStatus() {
    return acceptStatus;
  }

  public void setAcceptStatus(boolean acceptStatus) {
    this.acceptStatus = acceptStatus;
  }

  public String getProblem() {
    return problem;
  }

  public void setProblem(String problem) {
    this.problem = problem;
  }

  public String  getBookingDate() {
    return bookingDate;
  }

  public void setBookingDate(String bookingDate) {
    this.bookingDate = bookingDate;
  }

  public String getBookingTime() {
    return bookingTime;
  }

  public void setBookingTime(String bookingTime) {
    this.bookingTime = bookingTime;
  }

  public boolean isLive() {
    return isLive;
  }

  public double getMecLatitude() {
    return mecLatitude;
  }

  public double getMecLongitude() {
    return mecLongitude;
  }

  public void setMecLongitude(double mecLongitude) {
    this.mecLongitude = mecLongitude;
  }

  public void setMecLatitude(double mecLatitude) {
    this.mecLatitude = mecLatitude;
  }

  public void setLive(boolean live) {
    isLive = live;
  }


}



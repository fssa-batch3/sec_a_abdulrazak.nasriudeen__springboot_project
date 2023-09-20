package com.reparo.model;

import jakarta.persistence.*;



@Entity
@Table(name = "workshop")
public class Workshop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "workshop_id")
    private int workshopId;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;



    @Column(name = "workshop_name")
    private String workShopName;

    private String image;
    private String city;
    private String state;
    private String country;
    private String address;
    private double latitude;
    private double longitude;
    private int type;
    private String openTime;
    private String closeTime;
    private int generalPrice;
    private int electricalPrice;
    private int suspensionPrice;
    private int enginePrice;

    // Constructor
    public Workshop() {
        // Default constructor
    }

    // Getters and Setters

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getWorkShopName() {
        return workShopName;
    }

    public void setWorkShopName(String workShopName) {
        this.workShopName = workShopName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public int getGeneralPrice() {
        return generalPrice;
    }

    public void setGeneralPrice(int generalPrice) {
        this.generalPrice = generalPrice;
    }

    public int getElectricalPrice() {
        return electricalPrice;
    }

    public void setElectricalPrice(int electricalPrice) {
        this.electricalPrice = electricalPrice;
    }

    public int getSuspensionPrice() {
        return suspensionPrice;
    }

    public void setSuspensionPrice(int suspensionPrice) {
        this.suspensionPrice = suspensionPrice;
    }

    public int getEnginePrice() {
        return enginePrice;
    }



    public int getWorkShopId() {
        return workshopId;
    }

    public void setWorkShopId(int workShopId) {
        this.workshopId = workShopId;
    }


    public void setEnginePrice(int enginePrice) {
        this.enginePrice = enginePrice;
    }
}


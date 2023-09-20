package com.reparo.dto.service;

public class ServiceDto {
    private int serviceId;
    private int serviceListId;
    private int servicePrice;
    private String serviceName;

    public ServiceDto(int serviceListId , String serviceName , int servicePrice) {
        this.serviceListId = serviceListId;
        this.serviceName = serviceName;
        this.servicePrice =  servicePrice;
    }

    public ServiceDto() {
    }

    public int getServiceListId() {
        return serviceListId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public int getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(int servicePrice) {
        this.servicePrice = servicePrice;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setServiceListId(int serviceListId) {
        this.serviceListId = serviceListId;
    }
}

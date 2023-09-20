package com.reparo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "service_list")
public class ServiceList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int serviceId;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "service_price")
    private int servicePrice;

    @ManyToOne
    @JoinColumn(name = "service_detail_id")
    private ServiceDetail serviceDetail;


    public int getServiceId() {
        return serviceId;
    }

    public int getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(int servicePrice) {
        this.servicePrice = servicePrice;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public ServiceDetail getServiceDetail() {
        return serviceDetail;
    }

    public void setServiceDetail(ServiceDetail serviceDetail) {
        this.serviceDetail = serviceDetail;
    }
}

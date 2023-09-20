package com.reparo.dto.workshop;

public class WorkshopDistanceResponseDto  {
    private WorkshopResponseDto workshopInfo;
    private double distance ;


    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
    public WorkshopResponseDto getWorkshopInfo() {
        return workshopInfo;
    }

    public void setWorkshopInfo(WorkshopResponseDto workshopInfo) {
        this.workshopInfo = workshopInfo;
    }
}

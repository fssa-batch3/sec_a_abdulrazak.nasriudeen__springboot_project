package com.reparo.datamapper;

import com.reparo.dto.workshop.WorkshopRequestDto;
import com.reparo.dto.workshop.WorkshopResponseDto;
import com.reparo.model.Workshop;

public class WorkshopMapper {
    private final UserMapper map =  new UserMapper();
    public Workshop mapRequestToWorkshop(WorkshopRequestDto data){
        Workshop workshop = new Workshop();
        workshop.setWorkShopName(data.getWorkshopName());
        workshop.setImage(data.getWorkshopImage());
        workshop.setCity(data.getWorkshopCity().toLowerCase());
        workshop.setState(data.getWorkshopState());
        workshop.setCountry(data.getWorkshopCountry());
        workshop.setAddress(data.getWorkshopAddress());
        workshop.setLatitude(data.getLatitude());
        workshop.setLongitude(data.getLongitude());
        workshop.setType(data.getWorkshopType());
        workshop.setOpenTime(data.getWorkshopOpenTime());
        workshop.setCloseTime(data.getWorkshopCloseTime());
        workshop.setGeneralPrice(data.getGeneralServicePrice());
        workshop.setElectricalPrice(data.getElectricalServicePrice());
        workshop.setEnginePrice(data.getEngineServicePrice());
        workshop.setSuspensionPrice(data.getSuspensionServicePrice());
        return workshop;
    }
    public WorkshopResponseDto mapWorkshopToResponse(Workshop workshop){
        WorkshopResponseDto workshopResponseDto =  new WorkshopResponseDto();
        workshopResponseDto.setWorkshopId(workshop.getWorkShopId());
        workshopResponseDto.setUserId(workshop.getUser().getId());
        workshopResponseDto.setUser(map.mapUserToResponse(workshop.getUser()));
        workshopResponseDto.setWorkshopName(workshop.getWorkShopName());
        workshopResponseDto.setWorkshopImage(workshop.getImage());
        workshopResponseDto.setWorkshopCity(workshop.getCity());
        workshopResponseDto.setWorkshopState(workshop.getState());
        workshopResponseDto.setWorkshopCountry(workshop.getCountry());
        workshopResponseDto.setWorkshopAddress(workshop.getAddress());
        workshopResponseDto.setWorkshopType(workshop.getType());
        workshopResponseDto.setWorkshopOpenTime(workshop.getOpenTime());
        workshopResponseDto.setWorkshopCloseTime(workshop.getCloseTime());
        workshopResponseDto.setGeneralServicePrice(workshop.getGeneralPrice());
        workshopResponseDto.setElectricalServicePrice(workshop.getElectricalPrice());
        workshopResponseDto.setEngineServicePrice(workshop.getEnginePrice());
        workshopResponseDto.setSuspensionServicePrice(workshop.getSuspensionPrice());
        return workshopResponseDto;
    }

}

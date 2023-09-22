package com.reparo.service;

import com.reparo.dto.service.RejectServiceListDto;
import com.reparo.dto.service.ServiceDto;
import com.reparo.dto.service.ServiceListResponseDto;
import com.reparo.exception.ServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
 class ServiceListTest {
    @Autowired
    private ServiceListService listService;

    @Test
    void createServiceListTest(){
        try {
          assertNotEquals(0,listService.createServiceList(10));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }


    }
    @Test
    void createServiceTest(){
        ServiceDto dto =  new ServiceDto();
        dto.setServiceName("puncture");
        dto.setServicePrice(30);
        dto.setServiceListId(1);
        try {
            assertNotEquals(0,listService.createService(dto));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    void updateServiceTest(){
        ServiceDto dto =  new ServiceDto();
        dto.setServiceName("pure");
        dto.setServicePrice(30);
        dto.setServiceListId(1);
        dto.setServiceId(1);
        try {
            assertNotEquals(0,listService.updateService(dto));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    void deleteServiceTest(){
        try {
            listService.deleteService(1);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void acceptStatusTest(){
        try {
            assertTrue(listService.acceptServiceList(1));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    void cancelServiceListTest(){
        try {
            RejectServiceListDto dto = new RejectServiceListDto();
            dto.setServiceListId(1);
            dto.setCancelReason("high cost");
            assertTrue(listService.rejectServiceList(dto));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void getServiceListByIdTest(){
        try {
           ServiceListResponseDto dto = listService.getServiceListById(10);
           System.out.println(dto.getListOfServices().isEmpty());

        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    void makeServiceListLive(){
        try {
            assertTrue(listService.makeServiceListLive(1));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }




}

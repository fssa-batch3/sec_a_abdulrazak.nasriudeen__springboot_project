package com.reparo.service;

import com.reparo.dto.service.ServiceDto;
import com.reparo.exception.ServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
            assertTrue(listService.updateService(dto));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

    }


}

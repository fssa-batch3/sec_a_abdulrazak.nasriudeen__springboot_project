package com.reparo.service;

import com.reparo.exception.ServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
 class ServiceListTest {
    @Autowired
    private ServiceListService listService;

    @Test
    void createServiceTest(){
        try {
          assertNotEquals(0,listService.createServiceList(10));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }


    }


}

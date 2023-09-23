package com.reparo.service;
import com.reparo.dto.booking.BookingResponseDto;
import com.reparo.dto.workshop.WorkshopDistanceResponseDto;
import com.reparo.dto.workshop.WorkshopResponseDto;
import com.reparo.exception.ServiceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
 class WorkshopServiceTest {
    @Autowired
    private WorkshopService workshopService;

//    @Test
//    void saveWorkshop(){
//        WorkshopRequestDto requestDto =  new WorkshopRequestDto();
//        requestDto.setUserId(1002);
//        requestDto.setCity("chennai");
//        requestDto.setState("tamilNadu");
//        requestDto.setCountry("India");
//        requestDto.setWorkshopName("automobile");
//        requestDto.setAddress("123 cross street");
//        requestDto.setType(2);
//        requestDto.setLatitude(45.678);
//        requestDto.setLongitude(180.0);
//        requestDto.setElectricalPrice(3000);
//        requestDto.setEnginePrice(4000);
//        requestDto.setSuspensionPrice(300);
//        requestDto.setGeneralPrice(500);
//        requestDto.setOpenTime("23:32");
//        requestDto.setCloseTime("20:21");
//        try {
//         int id =   workshopService.createWorkshop(requestDto);
//            Assertions.assertNotNull(workshopRepository.findById(id));
//            System.out.println(id);
//        } catch (ServiceException e) {
//            throw new RuntimeException(e);
//        }
//    }
    @Test
    void getAllWorkshopTest(){
        try {
            List<WorkshopResponseDto> workshops =workshopService.getAllWorkshops(2);
            Assertions.assertFalse(workshops.isEmpty());
        } catch (ServiceException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    @Test
    void workshopExistTest(){
        try {
           assertTrue(workshopService.isWorkshopExist(52)) ;
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void findNearByWorkshopTest(){
        try {
            List<WorkshopDistanceResponseDto> workshops = workshopService.findWorkshopsNearByArea(13.13493200000,80.24788780000,"chennai");
            assertFalse(workshops.isEmpty());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void findNearByBooking(){
        try {
            List<BookingResponseDto> dto  = workshopService.getAllUnAcceptedBookingByWorkshopId(202);
            assertTrue(dto.isEmpty());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void getWorkshopByUserIdTest()   {
        try {
            assertNotNull(workshopService.getWorkshopByUserId(1302));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void getNearWorkshops(){
        try {
            assertTrue(workshopService.isWorkshopPresentNearByArea("chennai"));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
}

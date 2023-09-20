package com.reparo.controller;

import com.reparo.exception.ServiceException;
import com.reparo.service.ServiceListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("service")
@CrossOrigin(origins = "http://127.0.0.1:5501")
public class ServiceController {
    @Autowired
    private ServiceListService listService;

    @GetMapping("/createServiceList")
    public ResponseEntity<String> createServiceList(@RequestParam  int bookingId ) {
        try {
           int id = listService.createServiceList(bookingId);
           return ResponseEntity.ok(Integer.toString(id));
        } catch (ServiceException e) {
            return ResponseEntity.notFound().build();
        }


    }
}

package com.reparo.controller;

import com.reparo.dto.user.UserRequestDto;
import com.reparo.dto.user.UserResponseDto;
import com.reparo.exception.ServiceException;
import com.reparo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://127.0.0.1:5501")
public class UserController {
@Autowired
private  UserService userService ;
    @PostMapping("/createUser")
    public ResponseEntity<String> createResource(@RequestBody UserRequestDto request) {
        try {
          int id = userService.createUser(request);
            return ResponseEntity.ok(Integer.toString(id));
        } catch (ServiceException e) {
            // If a ServiceException occurs, return an error response with a 400 Bad Request status code
            return ResponseEntity.ok(e.getMessage());
        }
    }
    @GetMapping("/findByNum")
    public ResponseEntity<String>getUserByNumber(@RequestParam("number") long number){
        try {
            UserResponseDto resp = userService.findUserByNumber(number);
            return ResponseEntity.ok(resp.toString());
        } catch (ServiceException e) {
            // If a ServiceException occurs, return an error response with a 400 Bad Request status code
            return ResponseEntity.ok(e.getMessage());
        }

    }
    @GetMapping("/findById")
    public ResponseEntity<String>getUserById(@RequestParam("id") int id){
        try {
            UserResponseDto resp = userService.findUserById(id);
            return ResponseEntity.ok(resp.toString());
        } catch (ServiceException e) {
            return ResponseEntity.ok(e.getMessage());
        }

    }



    @PostMapping("/loginUser")
    public ResponseEntity<String> loginUser(@RequestBody UserRequestDto requestDto){
        try {
            UserResponseDto responseDto =  userService.loginUser(requestDto);
            return ResponseEntity.ok(responseDto.toString());

        }catch (ServiceException e ){
            return ResponseEntity.ok(e.getMessage());

        }
    }

}

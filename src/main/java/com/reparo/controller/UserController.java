package com.reparo.controller;

import com.reparo.dto.ApiResponse;
import com.reparo.dto.user.UserRequestDto;
import com.reparo.dto.user.UserResponseDto;
import com.reparo.exception.ServiceException;
import com.reparo.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:3000", "http://127.0.0.1:5501"})
public class UserController {
@Autowired
private  UserService userService ;
    @PostMapping("/reparo/user/createUser")
    public ResponseEntity<ApiResponse> createResource(@RequestBody UserRequestDto request) {
        try {
          int id = userService.createUser(request);
          JSONObject obj =  new JSONObject();
          obj.put("id",id);
          String str =  obj.toString();
            ApiResponse response =  new ApiResponse(ApiResponse.SUCCESS_CODE,ApiResponse.SUCCESS);
            response.setData(str);
            return ResponseEntity.ok(response);
        } catch (ServiceException e) {
            ApiResponse response =  new ApiResponse(ApiResponse.FAIL_CODE,ApiResponse.FAILED,e.getMessage());
            return ResponseEntity.ok(response);
        }
    }
    @PostMapping("/reparo/user/loginUser")
    public ResponseEntity<ApiResponse> loginUser(@RequestBody UserRequestDto requestDto){
        try {
            UserResponseDto userDto =  userService.loginUser(requestDto);
            JSONObject obj =  new JSONObject(userDto);

            ApiResponse response =  new ApiResponse(ApiResponse.SUCCESS_CODE,ApiResponse.SUCCESS);
            response.setData(obj.toString());
            return ResponseEntity.ok(response);

        }catch (ServiceException e ){
            return ResponseEntity.ok(new ApiResponse(ApiResponse.FAIL_CODE,ApiResponse.FAILED,e.getMessage()));

        }
    }
    @GetMapping("/reparo/user/findByNum")
    public ResponseEntity<ApiResponse>getUserByNumber(@RequestParam("number") long number){
        try {
            UserResponseDto userDto = userService.findUserByNumber(number);
            JSONObject obj =  new JSONObject(userDto);
            ApiResponse response =  new ApiResponse(ApiResponse.SUCCESS_CODE,ApiResponse.SUCCESS);
            response.setData(obj.toString());
            return ResponseEntity.ok(response);
        } catch (ServiceException e) {

            return ResponseEntity.ok(new ApiResponse(ApiResponse.FAIL_CODE,ApiResponse.FAILED,e.getMessage()));
        }

    }
    @GetMapping("/reparo/user/findById")
    public ResponseEntity<ApiResponse>getUserById(@RequestParam("id") int id){
        try {
            UserResponseDto userDto = userService.findUserById(id);
            JSONObject obj =  new JSONObject(userDto);
            ApiResponse response =  new ApiResponse(ApiResponse.SUCCESS_CODE,ApiResponse.SUCCESS);
            response.setData(obj.toString());
            return ResponseEntity.ok(response);
        } catch (ServiceException e) {
            return ResponseEntity.ok(new ApiResponse(ApiResponse.FAIL_CODE,ApiResponse.FAILED,e.getMessage()));
        }

    }





}

package com.reparo.service;


import com.reparo.dto.user.UserRequestDto;
import com.reparo.exception.ServiceException;
import com.reparo.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UserServiceTest {
@Autowired
private UserRepository userRepository;
@Autowired
private  UserService userService;
    @Test
    void saveUser(){
        UserRequestDto use = new UserRequestDto("abdul",9840326188L,"abd123",2);
        try {
           int id = userService.createUser(use);
            assertNotNull(userRepository.findById(id));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    void loginUser(){
                UserRequestDto use = new UserRequestDto();
                use.setPassword("abd123");
                use.setNumber(9840326188L);
        try {
          Assertions.assertNotNull(userService.loginUser(use));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }


    }
    @Test
    void findUserByIdTest(){
        try {
            Assertions.assertNotNull(userService.findUserById(2));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}

package com.reparo.service;

import com.reparo.validation.Validation;
import com.reparo.datamapper.UserMapper;
import com.reparo.dto.user.UserRequestDto;
import com.reparo.dto.user.UserResponseDto;
import com.reparo.exception.ServiceException;
import com.reparo.exception.ValidationException;
import com.reparo.model.User;
import com.reparo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService{
    public UserService() {
    }

    @Autowired
    private  UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    private final UserMapper map = new UserMapper();
    private final Validation validate =  new Validation();
    public boolean isUserExist(int id) throws ServiceException{
        User user =  new User();
        if(userRepository !=  null){
            user =  userRepository.findUserById(id);
            if(user ==  null)throw  new ServiceException("User Not present");
        }
        return user.getId()!= 0;

    }

    public int createUser(UserRequestDto userDto) throws ServiceException{
        try {
            User user = map.mapRequestDtoToUser(userDto);
            validate.userCredentialValidation(user);
            int id = 0 ;
            if(userRepository!=null){
                User existUser =  userRepository.findUserByNumber(user.getNumber());
                if(existUser!=null)throw new ServiceException("User Already present");
                User user1 = userRepository.save(user);
                id = user1.getId();
            }


            return id;
        } catch (ValidationException e) {
            throw new ServiceException(e.getMessage());
        }

    }
    public UserResponseDto findUserByNumber(long number) throws ServiceException{
        User user = new User();
        if (userRepository != null) {
            User existUser = userRepository.findUserByNumber(number);
            if(existUser==null)throw new ServiceException("User not present");
            user = existUser;
        }

        return map.mapUserToResponse(user);

    }
    public UserResponseDto loginUser(UserRequestDto request) throws ServiceException{
        try {
            validate.loginCredentialValidation(request);
            User user = new User();
            if (userRepository != null) {
                User existUser = userRepository.findUserByNumber(request.getNumber());
                if(existUser==null)throw new ServiceException("User not present");
                if(!(existUser.getPassword().equals(request.getPassword())))throw new ServiceException("number or password is incorrect");
                user = existUser;
                user.setLogin(true);
                userRepository.save(user);
            }

            return map.mapUserToResponse(user);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }


    }
    public UserResponseDto findUserById(int id) throws  ServiceException{
        try {
            UserResponseDto  dto  =  new UserResponseDto();
            if(userRepository != null){
                User user = userRepository.findUserById(id);
                if(user == null)throw new ServiceException("User not present ");
                dto = map.mapUserToResponse(user);


            }
            return dto;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

}

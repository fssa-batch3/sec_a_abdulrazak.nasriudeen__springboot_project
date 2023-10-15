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
import java.util.Base64;

@Service
public class UserService extends UserPassword{
    public UserService() {
    }

    @Autowired
    private  UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    private final UserMapper map = new UserMapper();
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
            Validation.userCredentialValidation(user);
            int id = 0 ;
            if(userRepository!=null){
                User existUser =  userRepository.findUserByNumber(user.getNumber());
                if(existUser!=null)throw new ServiceException("User Already present");
                byte[] salt = generateSalt();
                byte[] derivedKey = deriveKey(user.getPassword(), salt);
                user.setSalt(Base64.getEncoder().encodeToString(salt));
                user.setPassword(Base64.getEncoder().encodeToString(derivedKey));
                User user1 = userRepository.save(user);
                id = user1.getId();
            }


            return id;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }

    }
    public UserResponseDto findUserByNumber(long number) throws ServiceException{
        try {
            Validation.numberValidation(number);
            User user = new User();
            if (userRepository != null) {
                User existUser = userRepository.findUserByNumber(number);
                if(existUser==null)throw new ServiceException("User not present");
                user = existUser;
            }
            return map.mapUserToResponse(user);
        } catch (ValidationException e) {
            throw new ServiceException(e.getMessage());
        }

    }
    public UserResponseDto loginUser(UserRequestDto request) throws ServiceException{
        try {
            Validation.loginCredentialValidation(request);
            User user = new User();
            if (userRepository != null) {
                User existUser = userRepository.findUserByNumber(request.getNumber());
                if(existUser==null)throw new ServiceException("User not present");
                boolean verify = verifyPassword(request.getPassword(),existUser.getSalt(), existUser.getPassword());
                if(!verify)throw new ServiceException("number or password is incorrect");
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
            if ( isUserExist(id)) {
                if(userRepository != null){
                    User user = userRepository.findUserById(id);
                    dto = map.mapUserToResponse(user);
                }
            }
                return dto;

        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }





}

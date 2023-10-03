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

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;

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
                byte[] salt = generateSalt();
                byte[] derivedKey = deriveKey(user.getPassword(), salt, 10000, 200);
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

    protected byte[] generateSalt() {
        // Generate a random salt (usually 16 bytes)
        byte[] salt = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
        return salt;
    }
    protected static byte[] deriveKey(String password, byte[] salt, int iterations, int keyLength) throws Exception {
        // Create a PBEKeySpec with the provided password, salt, and iteration count
        KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, iterations, keyLength);

        // Use a SecretKeyFactory to derive the key
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        return factory.generateSecret(keySpec).getEncoded();
    }



}

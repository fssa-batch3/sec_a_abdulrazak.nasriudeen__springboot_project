package com.reparo.datamapper;

import com.reparo.dto.user.UserRequestDto;
import com.reparo.dto.user.UserResponseDto;
import com.reparo.model.User;

public class UserMapper {
    public User mapRequestDtoToUser(UserRequestDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setNumber(dto.getNumber());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        return user;
    }

    public UserResponseDto mapUserToResponse(User user){
        return new UserResponseDto(user.getId(), user.getName(), user.getNumber(), user.getRole());
    }


}

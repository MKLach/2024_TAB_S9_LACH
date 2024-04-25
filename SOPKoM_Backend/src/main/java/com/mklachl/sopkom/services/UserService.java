package com.mklachl.sopkom.services;

import com.mklachl.sopkom.model.dto.UserDto;
import com.mklachl.sopkom.model.entity.User;

public interface UserService {

    void saveUser(User user);
    
    void saveUser(UserDto userDto);

    User findUserByLogin(String email);

	User findUserById(Long id);

}

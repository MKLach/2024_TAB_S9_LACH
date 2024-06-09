package com.mklachl.sopkom.services;

import com.mklachl.sopkom.model.dto.UserDto;
import com.mklachl.sopkom.model.entity.User;

/**
 * Interfejs serwisu dla obsługi użytkowników
 */
public interface UserService {

    /**
     * Zapisuje nowego użytkownika
     * @param user encja użytkownika
     */
    void saveUser(User user);
    
    /**
     * Zapisuje nowego użytkownika na podstawie DTO
     * @param userDto DTO użytkownika
     */
    void saveUser(UserDto userDto);

    /**
     * Znajduje użytkownika po loginie
     * @param email login
     * @return znaleziony użytkownik
     */
    User findUserByLogin(String email);

    /**
     * Znajduje użytkownika po ID
     * @param id ID użytkownika
     * @return znaleziony użytkownik
     */
    User findUserById(Long id);

}

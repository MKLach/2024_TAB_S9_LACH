package com.mklachl.sopkom.repository;

import org.springframework.data.repository.CrudRepository;
import com.mklachl.sopkom.model.entity.User;

/**
 * Interfejs repository dla encji User
 */
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * Znajduje użytkownika po loginie
     * @param login login
     * @return Użytkownik
     */
    User findByLogin(String login);
}

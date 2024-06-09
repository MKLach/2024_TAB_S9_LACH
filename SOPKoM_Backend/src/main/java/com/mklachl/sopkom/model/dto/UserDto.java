package com.mklachl.sopkom.model.dto;

import com.mklachl.sopkom.model.entity.Role;
import com.mklachl.sopkom.model.entity.User;
import java.util.Set;

/**
 * Data Transfer Object (DTO) dla encji User.
 */
public class UserDto {

    /**
     * Identyfikator użytkownika.
     */
    private Long id;

    /**
     * Nazwa użytkownika.
     */
    private String name;

    /**
     * Email użytkownika.
     */
    private String email;

    /**
     * Hasło użytkownika.
     */
    private String password;

    /**
     * Role użytkownika.
     */
    private Set<Role> roles;

    /**
     * Nazwa roli użytkownika.
     */
    private String role;

    /**
     * Konstruktor domyślny.
     */
    public UserDto() {
    }

    /**
     * Konstruktor przyjmujący obiekt encji User.
     * @param user Obiekt encji User.
     */
    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getLogin();
        // this.password = user.getPassword();
        this.roles = user.getRoles();

        if (this.roles.size() > 0) {
            role = this.roles.iterator().next().toString();
        }
    }

    // Getters

    /**
     * Zwraca identyfikator użytkownika.
     * @return id Identyfikator użytkownika.
     */
    public Long getId() {
        return id;
    }

    /**
     * Ustawia identyfikator użytkownika.
     * @param id Identyfikator użytkownika.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Zwraca nazwę użytkownika.
     * @return name Nazwa użytkownika.
     */
    public String getName() {
        return name;
    }

    /**
     * Ustawia nazwę użytkownika.
     * @param name Nazwa użytkownika.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Zwraca email użytkownika.
     * @return email Email użytkownika.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Ustawia email użytkownika.
     * @param email Email użytkownika.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Zwraca hasło użytkownika.
     * @return password Hasło użytkownika.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Ustawia hasło użytkownika.
     * @param password Hasło użytkownika.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Zwraca role użytkownika.
     * @return roles Role użytkownika.
     */
    public Set<Role> getRoles() {
        return roles;
    }

    /**
     * Ustawia role użytkownika.
     * @param roles Role użytkownika.
     */
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    /**
     * Zwraca nazwę roli użytkownika.
     * @return role Nazwa roli użytkownika.
     */
    public String getRole() {
        return role;
    }

    /**
     * Ustawia nazwę roli użytkownika.
     * @param role Nazwa roli użytkownika.
     */
    public void setRole(String role) {
        this.role = role;
    }
}

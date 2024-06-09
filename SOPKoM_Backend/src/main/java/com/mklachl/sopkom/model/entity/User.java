package com.mklachl.sopkom.model.entity;

import java.util.Set;
import jakarta.persistence.*;

/**
 * Klasa reprezentująca encję User.
 */
@Entity
@Table(name="users")
public class User {

    /**
     * Identyfikator użytkownika.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Nazwa użytkownika.
     */
    private String name;

    /**
     * Login użytkownika.
     */
    private String login;

    /**
     * Hasło użytkownika.
     */
    private String password;

    /**
     * Role użytkownika.
     */
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    /**
     * Konstruktor domyślny.
     */
    public User() {
    }

    /**
     * Konstruktor parametryczny.
     * @param id Identyfikator użytkownika.
     * @param name Nazwa użytkownika.
     * @param email Login użytkownika.
     * @param password Hasło użytkownika.
     */
    public User(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.login = email;
        this.password = password;
    }

    /**
     * Konstruktor parametryczny z rolami.
     * @param id Identyfikator użytkownika.
     * @param name Nazwa użytkownika.
     * @param email Login użytkownika.
     * @param password Hasło użytkownika.
     * @param roles Role użytkownika.
     */
    public User(Long id, String name, String email, String password, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.login = email;
        this.password = password;
        this.roles = roles;
    }

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
     * Zwraca login użytkownika.
     * @return login Login użytkownika.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Ustawia login użytkownika.
     * @param login Login użytkownika.
     */
    public void setLogin(String login) {
        this.login = login;
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
}

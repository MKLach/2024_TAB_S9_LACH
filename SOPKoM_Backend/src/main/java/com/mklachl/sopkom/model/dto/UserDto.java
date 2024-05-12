package com.mklachl.sopkom.model.dto;

import java.util.Set;

import com.mklachl.sopkom.model.entity.Role;
import com.mklachl.sopkom.model.entity.User;

public class UserDto {

    private Long id;

    private String name;

    private String email;

    private String password;

    private Set<Role> roles;

    private String role;

    public UserDto(){

    }
    
    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getLogin();
        //this.password = user.getPassword();
        this.roles = user.getRoles();

        if(this.roles.size() > 0) {
        	role = this.roles.iterator().next().toString();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
    
    
}

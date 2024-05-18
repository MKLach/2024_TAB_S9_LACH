package com.mklachl.sopkom.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mklachl.sopkom.model.entity.Role;
import com.mklachl.sopkom.model.entity.User;
import com.mklachl.sopkom.repository.UserRepository;
import com.mklachl.sopkom.services.UserService;

@Service
public class CommandLineRunnerTest implements CommandLineRunner {

    @Autowired
    public UserRepository userRepository;
    
    @Autowired
  	UserService userService;
    
    public static final String ADMIN_LOGIN = "admin";
    public static final String ADMIN_NAME = "admin";
    public static final String ADMIN_PASS = "admin";
    
    public static final String MANAGER_LOGIN = "manager";
    public static final String MANAGER_NAME = "manager";
    public static final String MANAGER_PASS = "manager";
    
    
    @Override
    @Transactional
    public void run(String... args) throws Exception {

        System.out.println("if you see this, then it worked :)");
        {
	        User adminIn = userRepository.findByLogin(ADMIN_LOGIN);
	        
	        if(adminIn == null) {
	        	adminIn = new User();
	        	adminIn.setLogin(ADMIN_LOGIN);
	        	adminIn.setName(ADMIN_NAME);
	        	adminIn.setPassword(ADMIN_PASS);
	        	
	        	Set<Role> adminRole = new HashSet<Role>();
	        	adminRole.add(Role.ADMIN);
	        	
	        	adminIn.setRoles(adminRole);
	        	
	        	userRepository.deleteAll();
	        	
	        	userService.saveUser(adminIn);
	        	
	        	System.out.println("Admin account has been created!!!");
	        	
	        }
	    }
        
        User managerIn = userRepository.findByLogin(MANAGER_LOGIN);
        
        if(managerIn == null) {
        	managerIn = new User();
        	managerIn.setLogin(MANAGER_LOGIN);
        	managerIn.setName(MANAGER_NAME);
        	managerIn.setPassword(MANAGER_PASS);
        	
        	Set<Role> adminRole = new HashSet<Role>();
        	adminRole.add(Role.MANAGER);
        	
        	managerIn.setRoles(adminRole);
        	
        	userService.saveUser(managerIn);
        	
        	System.out.println("Manager account has been created!!!");
        	
        }
        
       
    }
}
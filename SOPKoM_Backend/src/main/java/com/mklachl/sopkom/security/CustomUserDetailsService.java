package com.mklachl.sopkom.security;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mklachl.sopkom.model.entity.User;
import com.mklachl.sopkom.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login);
        System.out.println(login);
        
        if (user != null) {
            //code for granting the user privileges
            //List<GrantedAuthority> privileges = new LinkedList<>();
            //privileges.add(new SimpleGrantedAuthority("PROFI"));
            //return new org.springframework.security.core.userdetails.User(user.getEmail(),
                    //user.getPassword(),
                    //privileges);

            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getLogin())
                    .password(user.getPassword())
                    .roles(user.getRoles().stream().map(Enum::name).toArray(String[]::new))
                    .build();

        }else{
            throw new UsernameNotFoundException("Invalid username or password.");

        }
    }
}
package com.example.iprwcbackendcode.security.services;

import com.example.iprwcbackendcode.model.Role;
import com.example.iprwcbackendcode.model.User;
import com.example.iprwcbackendcode.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserFromAuth(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            return this.userRepository.findUserById(userDetails.getId());
        }
        throw new NullPointerException("User not found");
    }
}

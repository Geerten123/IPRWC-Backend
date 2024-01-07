package com.example.iprwcbackendcode.controller;

import com.example.iprwcbackendcode.model.ApiResponse;
import com.example.iprwcbackendcode.model.ERole;
import com.example.iprwcbackendcode.model.Role;
import com.example.iprwcbackendcode.model.User;
import com.example.iprwcbackendcode.payload.request.LoginRequest;
import com.example.iprwcbackendcode.payload.request.SignupRequest;
import com.example.iprwcbackendcode.payload.response.JwtResponse;
import com.example.iprwcbackendcode.repository.RoleRepository;
import com.example.iprwcbackendcode.repository.UserRepository;
import com.example.iprwcbackendcode.security.jwt.JwtUtils;
import com.example.iprwcbackendcode.security.services.UserDetailsImpl;
import com.example.iprwcbackendcode.security.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserService userService;

    @PostMapping("/signin")
    public ApiResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return new ApiResponse<>(HttpStatus.ACCEPTED, new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), roles));

    }

    @PostMapping("/signup")
    public ApiResponse registerUser(@Valid @RequestBody SignupRequest signUpRequest){
        if(userRepository.existsByUsername(signUpRequest.getUsername())){
            return new ApiResponse<>(HttpStatus.BAD_REQUEST, "Error: Username is already taken!");
        }

        User user = new User(signUpRequest.getUsername(), encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin" -> {
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                    }
                    default -> {
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                    }
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);

        return new ApiResponse<>(HttpStatus.ACCEPTED, "User registered successfully!");
    }

    @RequestMapping(value = "/getRoles", method = RequestMethod.GET)
    public ApiResponse GetRoles(){
        try {
            Set<Role> roles = this.userService.getUserFromAuth().getRoles();
            return new ApiResponse<>(HttpStatus.ACCEPTED, roles);
        } catch (NullPointerException e) {
            return new ApiResponse<>(HttpStatus.UNAUTHORIZED, e);
        }
    }

    @RequestMapping(value = "/isLoggedIn", method = RequestMethod.GET)
    public ApiResponse IsLoggedIn(){
        try {
            User user = this.userService.getUserFromAuth();
            return new ApiResponse<>(HttpStatus.ACCEPTED, true);
        } catch (NullPointerException e) {
            return new ApiResponse<>(HttpStatus.UNAUTHORIZED, false);
        }
    }
}

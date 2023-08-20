package com.clothify.fairyland.controller;

import com.clothify.fairyland.entity.Customer;
import com.clothify.fairyland.entity.Users;
import com.clothify.fairyland.models.AuthenticationRequest;
import com.clothify.fairyland.models.AuthenticationResponse;
import com.clothify.fairyland.repository.CustomerRepository;
import com.clothify.fairyland.repository.UsersRepository;
import com.clothify.fairyland.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AuthenticateController {
    @Autowired
    private JwtUtil jwtTokenUtil;
    @Autowired
   private MyUserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private UsersRepository usersRepository;
    @RequestMapping(value = "/authenticate",method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)throws Exception{

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword())
            );
        }catch (BadCredentialsException e){
            throw new Exception("false",e);
        }
        final UserDetails userDetails=userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String jwt=jwtTokenUtil.generateToken(userDetails);
        final String username=authenticationRequest.getUsername();
        final Optional<Users> users=usersRepository.findByUserName(username);
        final String role=users.get().getRole();

        final Integer id=role.equals("ROLE_USER")?users.get().getCustomerId():users.get().getId();

        return ResponseEntity.ok(new AuthenticationResponse(jwt, id,role));
    }
}

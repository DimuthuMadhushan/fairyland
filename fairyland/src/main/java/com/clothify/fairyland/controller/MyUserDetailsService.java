package com.clothify.fairyland.controller;

import com.clothify.fairyland.entity.MyUserDetails;
import com.clothify.fairyland.entity.Users;
import com.clothify.fairyland.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UsersRepository usersRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> user=usersRepository.findByUserName(username);
        user.orElseThrow(()->new UsernameNotFoundException("Not Found "+username));
        return user.map(MyUserDetails::new).get();
    }
}

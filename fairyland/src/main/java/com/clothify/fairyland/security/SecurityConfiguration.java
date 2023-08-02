package com.clothify.fairyland.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsService userDetailsService;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/item/post").hasRole("ADMIN")
                .antMatchers("/item/addimage").hasRole("ADMIN")
                .antMatchers("/item/delete/{id}").hasRole("ADMIN")
                .antMatchers("item/update/{id}").hasRole("ADMIN")
                .antMatchers("/customer/custList").hasRole("ADMIN")
                .antMatchers("/orders/order-detail/{id}").hasRole("ADMIN")
                .antMatchers("/orders/all-orders").hasRole("ADMIN")
                .antMatchers("/customer/update/{id}").hasAnyRole("ADMIN","USER")
                .antMatchers("/customer/delete/{id}").hasAnyRole("ADMIN","USER")
                .antMatchers("/orders/add/{custid}").hasAnyRole("ADMIN","USER")
                .antMatchers("/item/get-all").permitAll()
                .antMatchers("/customer/add-customer").permitAll()
                .and().formLogin();

    }
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}

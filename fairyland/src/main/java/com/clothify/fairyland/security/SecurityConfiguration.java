package com.clothify.fairyland.security;

import com.clothify.fairyland.filters.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtRequestFilter jwtRequestFilter;
    @Autowired
    private  UserDetailsService userDetailsService;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/item/post").hasRole("ADMIN")
                .antMatchers("/item/addimage").hasRole("ADMIN")
                .antMatchers("/item/delete/{id}").hasRole("ADMIN")
                .antMatchers("item/update/{id}").hasRole("ADMIN")
                .antMatchers("/orders/order-detail/{id}").hasRole("ADMIN")
                .antMatchers("/orders/all-orders").hasRole("ADMIN")
                .antMatchers("/customer/custList").hasAnyRole("ADMIN","USER")
                .antMatchers("/customer/update/{id}").hasAnyRole("ADMIN","USER")
                .antMatchers("/customer/delete/{id}").hasAnyRole("ADMIN","USER")
                .antMatchers("/orders/add/{custid}").hasAnyRole("ADMIN","USER")
                .antMatchers("/customer/add-customer").permitAll()
                .antMatchers("/item/get-all").permitAll()
                .antMatchers("/authenticate").permitAll()
                .anyRequest().authenticated().and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}

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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

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
        http.csrf().disable().cors().and().authorizeHttpRequests()
                .antMatchers("/item/post").hasRole("ADMIN")
                .antMatchers("/item/addimage").hasRole("ADMIN")
                .antMatchers("/item/delete/{id}").hasRole("ADMIN")
                .antMatchers("/item/update/{id}").hasRole("ADMIN")
                .antMatchers("/orders/order-detail/{id}").hasRole("ADMIN")
                .antMatchers("/orders/all-orders").hasRole("ADMIN")
                .antMatchers("/customer/custList").hasRole("ADMIN")
                .antMatchers("/custname/{id}").hasRole("ADMIN")
                .antMatchers("/customer/delete/{id}").hasAnyRole("ADMIN","USER")
                .antMatchers("/orders/add/{custid}").hasAnyRole("ADMIN","USER")
                .antMatchers("/customer/update/{userName}").hasAnyRole("ADMIN","USER")
                .antMatchers("/customer/detail/{username}").hasAnyRole("ADMIN","USER")
                .antMatchers("/customer/add-customer").permitAll()
                .antMatchers("/item/get-all").permitAll()
                .antMatchers("/authenticate").permitAll()
                .antMatchers("/item/getimage/{filename}").permitAll()
                .antMatchers("/item/getItem/{id}").permitAll()
                .antMatchers("/item/men").permitAll()
                .antMatchers("/item/women").permitAll()
                .antMatchers("/item/kids").permitAll()
                .antMatchers("/item/new").permitAll()
                .anyRequest().authenticated().and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    }
    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration corsConfiguration=new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
        corsConfiguration.setAllowedMethods(Arrays.asList("*"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",corsConfiguration);
        return source;
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

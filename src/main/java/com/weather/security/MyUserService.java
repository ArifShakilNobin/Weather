package com.weather.security;


import com.weather.services.UserService;
import com.weather.services.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserService implements UserDetailsService {

    private final HttpServletRequest request;
    private final UserService userServices;
    public  MyUserService(UserServiceImpl userServices, HttpServletRequest request){
        this.request = request;
        this.userServices = userServices;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {



        com.weather.model.User userDetail = userServices.getUser(username);
        request.getSession().setAttribute("User", userDetail);
       return User.builder()
                .username(userDetail.getUserName())
                .password(userDetail.getPassword())
                .roles("USER")
                .build();



    }

}

package com.weather.services;

import com.weather.model.User;
import org.springframework.stereotype.Service;


@Service
public interface UserService {

    public User getUser(String userName);


    public void register(String userName, String password);
}

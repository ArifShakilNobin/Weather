package com.weather.services;

import com.weather.dao.UserRepository;
import com.weather.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User getUser(String userName){
        return userRepository.search(userName);
    }


    public void register(String userName, String password) {
        User user = new User();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        password = passwordEncoder.encode(password);
        user.setUserName(userName);
        user.setPassword(password);
        userRepository.save(user);
    }
}

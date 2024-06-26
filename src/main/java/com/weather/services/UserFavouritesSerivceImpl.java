package com.weather.services;


import com.weather.dao.UserFavouriteRepository;
import com.weather.detail.LocationInformation;
import com.weather.model.User;
import com.weather.model.UserFavourites;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserFavouritesSerivceImpl implements UserFavouritesSerivce{

    private final UserFavouriteRepository userFavouriteRepository;

    public UserFavouritesSerivceImpl(UserFavouriteRepository userFavouriteRepository) {
        this.userFavouriteRepository = userFavouriteRepository;
    }


    @Override
    public void addFavourite(int userId, LocationInformation locationInformation) {
        if(userId==0 || locationInformation == null) {
            return;
        }
        UserFavourites userFavourites = new UserFavourites();
        User user = new User();
        user.setId(userId);

        userFavourites.setLatitude(locationInformation.getLatitude());
        userFavourites.setLongitude(locationInformation.getLongitude());
        userFavourites.setCity(locationInformation.getName());
        userFavourites.setCountry(locationInformation.getCountry());
        userFavourites.setUser(user);
        userFavouriteRepository.save(userFavourites);
    }


    @Override
    public void removeFavourite(LocationInformation locationInformation,int userId) {
        if(userId==0 || locationInformation == null) {
            return;
        }
        UserFavourites userFavourites = userFavouriteRepository.get(locationInformation.getLatitude(), locationInformation.getLongitude(), userId);
        if(userFavourites != null) {
            userFavouriteRepository.delete(userFavourites);
        }
    }
    @Override
    public List<UserFavourites> getFavourites(int userId) {
        return userFavouriteRepository.findAllByUserId(userId);
    }
}

package com.weather.services;


import com.weather.detail.*;
import com.weather.model.*;

import java.util.List;

public interface UserFavouritesSerivce {
    public void addFavourite(int userId, LocationInformation locationInformation);
    public void removeFavourite(LocationInformation locationInformation,int userId);
    public List<UserFavourites> getFavourites(int userId);
}

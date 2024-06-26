package com.weather.dao;



import com.weather.model.UserFavourites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserFavouriteRepository extends JpaRepository<UserFavourites, Integer> {
    @Query("select c from user_favourites c where c.user.id =:userId")
    List<UserFavourites> findAllByUserId(@Param("userId") int userId);

    @Query("select c from user_favourites c where c.latitude =:latitude and c.longitude =:longitude and c.user.id =:userId")
    UserFavourites get(@Param("latitude") double latitude, @Param("longitude") double longitude, @Param("userId") int userId);
}

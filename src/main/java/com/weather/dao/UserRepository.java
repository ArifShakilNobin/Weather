package com.weather.dao;


import com.weather.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

//    @Query("select c.id,c.userName,c.password from User c where lower(c.userName)  = :userName ")
    @Query(value = "select c.id,c.user_name,c.password from \"user\" c where lower(c.user_name)  = :userName ",nativeQuery = true)
    User search(@Param("userName") String userName);


}

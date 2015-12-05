package com.boldijarpaul.polihack.service;

import com.boldijarpaul.polihack.mvp.model.User;

import retrofit.http.Body;
import retrofit.http.POST;
import rx.Observable;

public interface UserService {

    @POST("/data/users/add")
    Observable<User> loginUser(@Body User user);
}

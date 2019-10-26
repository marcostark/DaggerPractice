package com.example.daggerpractice.network.auth;

import com.example.daggerpractice.models.User;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AuthApi {

    @GET("users/{id}")
    Flowable<User> getUsers(
            @Path("id") int id
    );

}

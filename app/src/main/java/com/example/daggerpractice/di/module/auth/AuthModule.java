package com.example.daggerpractice.di.module.auth;

import android.app.Application;

import com.example.daggerpractice.network.auth.AuthApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class AuthModule {

    //Apenas as dependencias desse subcomponent
    @Provides
    static AuthApi provideAuthApi(Retrofit retrofit){
        return retrofit.create(AuthApi.class);
    }

}

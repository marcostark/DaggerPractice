package com.example.daggerpractice.di.module;

import android.app.Application;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.daggerpractice.R;
import com.example.daggerpractice.util.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Modulo principal da aplicação,
 * aqui serão declaradas dependências no nível do aplicativo
 * do projeto, e que serão imutaveis durante toda a vida útil
 * da aplicação. *
 * As dependencias aqui inseridas não estão
 * relacionadas com as atividades
 * */

@Module
public class AppModule {

    /**
     * Serão destruidos até que o
     * componente a que pertencem seja destruido
     * no caso o AppComponent*/


    @Singleton
    @Provides
    static Retrofit provideRetrofitInstance(){
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    static RequestOptions provideRequestOptions(){
        return RequestOptions
                .placeholderOf(R.drawable.white_background)
                .error(R.drawable.white_background);
    }


    /**
     * O componente Application está disponivel
     * dentro do modulo
     *
     * */
    @Singleton
    @Provides
    static RequestManager providerGlideInstance(Application application, RequestOptions requestOptions){
        return Glide.with(application)
                .setDefaultRequestOptions(requestOptions);
    }

    @Singleton
    @Provides
    static Drawable providerAppDrawable(Application application){
        return ContextCompat.getDrawable(application, R.drawable.logo);
    }

}

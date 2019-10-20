package com.example.daggerpractice.di.component;


/*
* Vai persistir em toda vida útil do aplicatico
* */

import android.app.Application;

import com.example.daggerpractice.BaseApplication;
import com.example.daggerpractice.di.module.ActivityBuilderModule;
import com.example.daggerpractice.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Carregando os principais modulos da aplicação, e
 * disponível apenas uma única instancia durante
 * toda a vida útil da aplicação
 * */
@Singleton
@Component(
        modules = {
                AndroidSupportInjectionModule.class,
                ActivityBuilderModule.class,
                AppModule.class,
        }
)
public interface AppComponent extends AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder{

        @BindsInstance
        Builder application(Application application);

        AppComponent build();

    }

}

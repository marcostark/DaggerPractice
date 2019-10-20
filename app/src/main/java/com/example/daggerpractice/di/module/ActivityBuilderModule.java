package com.example.daggerpractice.di.module;

import com.example.daggerpractice.AuthActivity;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;


/**
 * Responsavél por carregar os modulos das activitys,
 * todas as atividades são carregadas aqui.
 * Aqui serão inseridas apenas declarações das activitys
 * */

@Module
public abstract class ActivityBuilderModule {

    /**
     * Subcomponent Auth activity*/
    @ContributesAndroidInjector
    abstract AuthActivity contributeAuthActivity();

}

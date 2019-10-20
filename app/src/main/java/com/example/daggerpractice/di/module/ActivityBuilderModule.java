package com.example.daggerpractice.di.module;

import com.example.daggerpractice.di.module.auth.AuthViewModelModule;
import com.example.daggerpractice.ui.auth.AuthActivity;

import dagger.Module;
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
    @ContributesAndroidInjector(
            modules = {AuthViewModelModule.class} // Garantir que faça parte apenas desse sub-component
    )
    abstract AuthActivity contributeAuthActivity();

}

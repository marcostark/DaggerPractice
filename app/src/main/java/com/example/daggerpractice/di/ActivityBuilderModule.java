package com.example.daggerpractice.di;

import com.example.daggerpractice.AuthActivity;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;


/**
 * Responsavél por carregar os modulos das atividades,
 * todas as atividades são carregadas aqui
 * */

@Module
public abstract class ActivityBuilderModule {

    /**
     * Subcomponent Auth activity*/
    @ContributesAndroidInjector
    abstract AuthActivity contributeAuthActivity();

}

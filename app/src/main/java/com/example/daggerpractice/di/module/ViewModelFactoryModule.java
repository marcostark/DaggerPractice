package com.example.daggerpractice.di.module;

import androidx.lifecycle.ViewModelProvider;

import com.example.daggerpractice.viewmodels.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;

/**
 * Resposável por gerar a dependência
 * de injeção para a classe ViewModelFactory
 * */
@Module
public abstract class ViewModelFactoryModule {

    // Jeito mais eficiente de fornecer um dependência
    @Binds
    public abstract ViewModelProvider.Factory bindsViewModelFactory(
            ViewModelProviderFactory viewModelProviderFactory);

}

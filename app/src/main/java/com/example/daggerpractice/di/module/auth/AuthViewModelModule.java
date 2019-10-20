package com.example.daggerpractice.di.module.auth;

import androidx.lifecycle.ViewModel;

import com.example.daggerpractice.di.ViewModelKey;
import com.example.daggerpractice.ui.auth.AuthViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AuthViewModelModule {


    @Binds
    @IntoMap //Mapeando para uma chave especifica, multi ligação
    @ViewModelKey(AuthViewModel.class)
    public abstract ViewModel bindAuthViewModel(AuthViewModel viewModel);


}

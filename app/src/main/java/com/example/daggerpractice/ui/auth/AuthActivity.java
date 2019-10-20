package com.example.daggerpractice.ui.auth;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.RequestManager;
import com.example.daggerpractice.R;
import com.example.daggerpractice.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends DaggerAppCompatActivity {

    private AuthViewModel authViewModel;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    Drawable logo;

    @Inject
    RequestManager requestManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_main);

        authViewModel = ViewModelProviders.of(this, providerFactory).get(AuthViewModel.class);

        setLogo();

    }

    private void setLogo(){
        requestManager
                .load(logo)
                .into((ImageView)findViewById(R.id.login_logo));
    }
}

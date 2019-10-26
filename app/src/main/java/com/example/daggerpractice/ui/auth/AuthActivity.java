package com.example.daggerpractice.ui.auth;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.RequestManager;
import com.example.daggerpractice.R;
import com.example.daggerpractice.models.User;
import com.example.daggerpractice.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends DaggerAppCompatActivity implements View.OnClickListener {

    private static final String TAG = "AuthActivity";

    private AuthViewModel authViewModel;

    private EditText userId;

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
        userId = findViewById(R.id.user_id_input);

        authViewModel = ViewModelProviders.of(this, providerFactory).get(AuthViewModel.class);

        findViewById(R.id.login_button).setOnClickListener(this);

        setLogo();

        subscribeObservers();

    }

    private void setLogo(){
        requestManager
                .load(logo)
                .into((ImageView)findViewById(R.id.login_logo));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_button:{
                attemptLogin();
                break;
            }
        }
    }

    // MEtodo para observar o usuario retornado
    private void subscribeObservers(){
        authViewModel.observerUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if(user != null){
                    Log.i(TAG, "onChanged: " + user.getEmail());
                }
            }
        });
    }

    // Verifica se não foi informado nenhum texto
    private void attemptLogin(){
        if(TextUtils.isEmpty(userId.getText().toString())){
            return;
        }
        authViewModel.authenticatedWithId(Integer.parseInt(userId.getText().toString()));
    }
}

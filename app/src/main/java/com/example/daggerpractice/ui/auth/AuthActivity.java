package com.example.daggerpractice.ui.auth;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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
    private ProgressBar progressBar;

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
        progressBar = findViewById(R.id.progress_bar);

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
       authViewModel.observerUser().observe(this, new Observer<AuthResource<User>>() {
           @Override
           public void onChanged(AuthResource<User> userAuthResource) {
             if(userAuthResource != null){
                 switch (userAuthResource.status){
                     case LOADING: {
                            showProgressBar(true);
                         break;
                     }
                     case AUTHENTICATED: {
                         Log.i(TAG, "onChanged: Login Sucess: " + userAuthResource.data.getEmail());
                         showProgressBar(false);
                         break;
                     }
                     case ERROR: {
                         Toast.makeText(AuthActivity.this, userAuthResource.message
                                 + "\n Informe um número entre 1 e 10", Toast.LENGTH_SHORT).show();
                         showProgressBar(false);
                         break;
                     }
                     case NOT_AUTHENTICATED: {
                         showProgressBar(false);
                         break;
                     }
                 }
             }
           }
       });
    }

    private void showProgressBar(boolean isVIsible){
        if(isVIsible){
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    // Verifica se não foi informado nenhum texto
    private void attemptLogin(){
        if(TextUtils.isEmpty(userId.getText().toString())){
            return;
        }
        authViewModel.authenticatedWithId(Integer.parseInt(userId.getText().toString()));
    }
}

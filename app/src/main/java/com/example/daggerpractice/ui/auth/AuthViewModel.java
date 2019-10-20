package com.example.daggerpractice.ui.auth;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

public class AuthViewModel extends ViewModel {

    private static final String TAG = "AuthViewModel";

    @Inject
    public AuthViewModel(){
        Log.i(TAG, "AuthViewModel: viewmodel is working...");
    }

}

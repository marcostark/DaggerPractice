package com.example.daggerpractice.ui.auth;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.daggerpractice.models.User;
import com.example.daggerpractice.network.auth.AuthApi;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {

    private static final String TAG = "AuthViewModel";
    private final AuthApi authApi;

    @Inject
    public AuthViewModel(AuthApi authApi){
        this.authApi = authApi;
        Log.i(TAG, "AuthViewModel: viewmodel is working...");

       authApi.getUsers(1)
               .toObservable()
               .subscribeOn(Schedulers.io())
               .subscribe(new Observer<User>() {
                   @Override
                   public void onSubscribe(Disposable d) {

                   }

                   @Override
                   public void onNext(User user) {
                       Log.i(TAG, "onNext: " + user.getEmail());
                   }

                   @Override
                   public void onError(Throwable e) {
                       Log.i(TAG, "onError:", e );
                   }

                   @Override
                   public void onComplete() {

                   }
               });
    }

}

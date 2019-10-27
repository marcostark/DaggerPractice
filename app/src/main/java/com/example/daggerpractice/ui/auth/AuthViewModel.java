package com.example.daggerpractice.ui.auth;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.daggerpractice.models.User;
import com.example.daggerpractice.network.auth.AuthApi;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {

    private static final String TAG = "AuthViewModel";
    private final AuthApi authApi;

    private MediatorLiveData<AuthResource<User>> authUser= new MediatorLiveData<>();

    @Inject
    public AuthViewModel(AuthApi authApi){
        this.authApi = authApi;
        Log.i(TAG, "AuthViewModel: viewmodel is working...");

    }

    public void authenticatedWithId(int userId){
        authUser.setValue(AuthResource.loading((User)null)); //Informa a interface o estado de carregamento

        final LiveData<AuthResource<User>> source = LiveDataReactiveStreams.fromPublisher(
                authApi.getUsers(userId)

                        // Chamando o erro, apenas se o mesmo acontecer
                        .onErrorReturn(new Function<Throwable, User>() {
                            @Override
                            public User apply(Throwable throwable) throws Exception {
                                //Cria um usuário de erro
                                User errorUser = new User();
                                errorUser.setId(-1);
                                return errorUser;
                            }
                        })
                        .map(new Function<User, AuthResource<User>>() {
                            @Override
                            public AuthResource<User> apply(User user) throws Exception {
                                //Caso o id do usuário seja negativo ou seja retornado algum outro erro
                                if(user.getId()== -1){
                                    return AuthResource.error("Não autenticado", (User)null);
                                }
                                // Retornando usuario autenticado
                                return AuthResource.authenticated(user);
                            }
                        })
                .subscribeOn(Schedulers.io())
        );
        authUser.addSource(source, new Observer<AuthResource<User>>(){

            @Override
            public void onChanged(AuthResource<User> user) {
                authUser.setValue(user);
                authUser.removeSource(source);
            }
        });
    }

    public LiveData<AuthResource<User>> observerUser(){
        return authUser;
    }

}

package com.example.daggerpractice.di;

import dagger.Module;
import dagger.Provides;

/**
 * Modulo principal da aplicação,
 * aqui serão declaradas dependências no nível do aplicativo
 * do projeto, e que serão imutaveis durante toda a vida útil
 * da aplicação. *
 * As dependencias aqui inseridas não estão
 * relacionadas com as atividades
 * */

@Module
public class AppModule {

    @Provides
    static String anything(){
        return "Hello, world";
    }

}

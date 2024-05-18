package com.example.a2020102527_main_project_2.di
/*
    Surname and Initials: Makhooane KE
    Student Number: 2020102527
    Date: 16 May 2024
    Module Code: CSIP6853
*/
import org.jetbrains.annotations.Debug.Renderer
import javax.inject.Qualifier

/*
   These annotations define qualifiers for different dispatchers and scopes in dependency injection:

    DefaultDispatcher, IoDispatcher, MainDispatcher, MainImmediateDispatcher:
    - Qualify coroutine dispatchers for specific tasks.

    ApplicationScope:
    - Qualifies coroutine scope for application-level lifespan.
    - They enhance clarity and precision in distinguishing between various coroutine
      contexts and scopes in dependency injection setups.
 */
@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class DefaultDispatcher

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class IoDispatcher

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class MainDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainImmediateDispatcher

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope




package com.hnativ.androidlabpokedex.di

import android.app.Application
import com.hnativ.androidlabpokedex.domain.PokemonRepository
import com.hnativ.androidlabpokedex.persistance.AppDatabase
import com.hnativ.androidlabpokedex.presentation.list.PokemonListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface AppComponent {
    fun inject(fragment: PokemonListFragment)
    fun repository(): PokemonRepository
    fun application(): Application
    fun database(): AppDatabase
}
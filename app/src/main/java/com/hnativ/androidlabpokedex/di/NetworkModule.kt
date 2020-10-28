package com.hnativ.androidlabpokedex.di

import android.app.Application
import com.hnativ.androidlabpokedex.App
import com.hnativ.androidlabpokedex.data.PokedexApiService
import com.hnativ.androidlabpokedex.data.PokemonRepositoryImpl
import com.hnativ.androidlabpokedex.data.createPokedexApiService
import com.hnativ.androidlabpokedex.domain.PokemonRepository
import com.hnativ.androidlabpokedex.persistance.AppDatabase
import com.hnativ.androidlabpokedex.persistance.getDatabase
import dagger.Module
import dagger.Provides

@Module
class NetworkModule {

    @Provides
    fun providesApplication(): Application = App.instance

    @Provides
    fun providesApi(): PokedexApiService = createPokedexApiService()

    @Provides
    fun providesDatabase(app: Application): AppDatabase = getDatabase(app)

    @Provides
    fun providesRepository(
        api: PokedexApiService,
        database: AppDatabase
    ): PokemonRepository = PokemonRepositoryImpl(api, database)
}
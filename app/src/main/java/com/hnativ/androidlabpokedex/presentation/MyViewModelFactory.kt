package com.hnativ.androidlabpokedex.presentation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hnativ.androidlabpokedex.data.PokemonRepositoryImpl
import com.hnativ.androidlabpokedex.persistance.getDatabase
import com.hnativ.androidlabpokedex.presentation.details.PokemonDetailsViewModel
import com.hnativ.androidlabpokedex.presentation.list.PokemonListViewModel

class MyViewModelFactory(val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val database = getDatabase(app)
        val repository = PokemonRepositoryImpl(database)

        return when (modelClass) {
            PokemonListViewModel::class.java -> PokemonListViewModel(repository)
            PokemonDetailsViewModel::class.java -> PokemonDetailsViewModel(repository)
            else -> throw IllegalArgumentException("Unable to construct viewmodel")
        } as T
    }
}
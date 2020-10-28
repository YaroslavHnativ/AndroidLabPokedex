package com.hnativ.androidlabpokedex.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hnativ.androidlabpokedex.App
import com.hnativ.androidlabpokedex.presentation.details.PokemonDetailsViewModel
import com.hnativ.androidlabpokedex.presentation.list.PokemonListViewModel

class MyViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val repository = App.instance.appComponent.repository()

        return when (modelClass) {
            PokemonListViewModel::class.java -> PokemonListViewModel(repository)
            PokemonDetailsViewModel::class.java -> PokemonDetailsViewModel(repository)
            else -> throw IllegalArgumentException("Unable to construct viewmodel")
        } as T
    }
}
package com.hnativ.androidlabpokedex.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hnativ.androidlabpokedex.data.PokemonRepositoryImpl
import com.hnativ.androidlabpokedex.domain.PokemonDetails
import com.hnativ.androidlabpokedex.domain.PokemonRepository

class PokemonDetailsViewModel : ViewModel() {
    private val repository = PokemonRepositoryImpl()

    private val _pokemonDetailsLiveData = MutableLiveData<PokemonDetails>()
    val pokemonDetailsLiveData: LiveData<PokemonDetails>
        get() = _pokemonDetailsLiveData

    fun loadPokemonData(id: String) {
        repository.getPokemonById(id, object : PokemonRepository.ApiCallback<PokemonDetails> {
            override fun onSuccess(data: PokemonDetails) {
                _pokemonDetailsLiveData.postValue(data)
            }

            override fun onError() {
            }
        })
    }
}
package com.hnativ.androidlabpokedex.presentation.details

import androidx.lifecycle.*
import com.hnativ.androidlabpokedex.domain.PokemonDetails
import com.hnativ.androidlabpokedex.domain.PokemonRepository
import kotlinx.coroutines.launch

class PokemonDetailsViewModel(private val repository: PokemonRepository) : ViewModel() {

    private var _pokemonDetailsLiveData = MutableLiveData<PokemonDetails>()
    val pokemonDetailsLiveData: LiveData<PokemonDetails>
        get() = _pokemonDetailsLiveData


    fun loadPokemonData(id: String) {
        viewModelScope.launch {
            _pokemonDetailsLiveData.postValue(repository.getPokemonById(id))
        }
    }
}
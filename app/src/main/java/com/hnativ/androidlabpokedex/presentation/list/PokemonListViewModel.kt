package com.hnativ.androidlabpokedex.presentation.list

import androidx.lifecycle.*
import com.hnativ.androidlabpokedex.domain.Pokemon
import com.hnativ.androidlabpokedex.domain.PokemonRepository
import kotlinx.coroutines.launch
import java.io.IOException

class PokemonListViewModel(private val repository: PokemonRepository) : ViewModel() {

    private val _isLoadingLiveData = MutableLiveData<Boolean>()
    val isLoadingLiveData: LiveData<Boolean>
        get() = _isLoadingLiveData

    private val _contentLiveData = MutableLiveData<List<Pokemon>>()
    val contentLiveData: LiveData<List<Pokemon>>
        get() = _contentLiveData

    private val _isErrorLiveData = MutableLiveData<Boolean>()
    val isErrorLiveData: LiveData<Boolean>
        get() = _isErrorLiveData

    init {
        showLoading()
        viewModelScope.launch {
            try {
                if (repository.getPokemonList().isNullOrEmpty()) {
                    repository.refreshPokemonData()
                }
                showData(repository.getPokemonList())
            } catch (networkError: IOException) {
                showError()
            }
        }
    }

    private fun showData(data: List<Pokemon>) {
        _contentLiveData.postValue(data)
        _isLoadingLiveData.value = false
        _isErrorLiveData.value = false
    }

    private fun showError() {
        _contentLiveData.value = emptyList()
        _isLoadingLiveData.value = false
        _isErrorLiveData.value = true
    }

    private fun showLoading() {
        _contentLiveData.value = emptyList()
        _isLoadingLiveData.value = true
        _isErrorLiveData.value = false
    }
}
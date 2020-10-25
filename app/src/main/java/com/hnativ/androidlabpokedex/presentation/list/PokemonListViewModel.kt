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

    private val _contentLiveData = repository.pokemonList
    val contentLiveData: LiveData<List<Pokemon>>
        get() = _contentLiveData

    private val _isErrorLiveData = MutableLiveData<Boolean>()
    val isErrorLiveData: LiveData<Boolean>
        get() = _isErrorLiveData

    fun loadData() {

        viewModelScope.launch {
            try {
                if (repository.pokemonList.value.isNullOrEmpty()) {
//                    showLoading()
                    repository.refreshPokemonData()
//                    showData()
                }
            } catch (networkError: IOException) {
//                if (_contentLiveData.value.isNullOrEmpty())
//                    Log.i("PokemonListViewModel", "Content is empty")
            }
        }
    }


//    Handler().postDelayed(
//    {
//        if (Random.nextInt() % 10 == 0) {
//            showError()
//        } else {
//            viewModelScope.launch {
//                val data = repository.pokemonList
//                showData(data)
//            }
//        }
//    }, 3000)

    private fun showData() {
//        _contentLiveData = data as MutableLiveData<List<Pokemon>>
        _isLoadingLiveData.value = false
        _isErrorLiveData.value = false
    }

    private fun showError() {
//        _contentLiveData.value = emptyList()
        _isLoadingLiveData.value = false
        _isErrorLiveData.value = true
    }

    private fun showLoading() {
//        _contentLiveData.value = emptyList()
        _isLoadingLiveData.value = true
        _isErrorLiveData.value = false
    }
}
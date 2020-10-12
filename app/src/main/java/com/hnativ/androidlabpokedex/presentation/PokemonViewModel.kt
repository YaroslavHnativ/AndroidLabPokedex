package com.hnativ.androidlabpokedex.presentation

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hnativ.androidlabpokedex.data.PokemonRepositoryImpl
import com.hnativ.androidlabpokedex.domain.Pokemon
import kotlin.random.Random

class PokemonViewModel : ViewModel() {
    private val repository = PokemonRepositoryImpl()

    private val _isLoadingLiveData = MutableLiveData<Boolean>()
    val isLoadingLiveData: LiveData<Boolean>
        get() = _isLoadingLiveData

    private val _contentLiveData = MutableLiveData<List<Pokemon>>()
    val contentLiveData: LiveData<List<Pokemon>>
        get() = _contentLiveData

    private val _isErrorLiveData = MutableLiveData<Boolean>()
    val isErrorLiveData: LiveData<Boolean>
        get() = _isErrorLiveData

    fun loadData() {
        _contentLiveData.value = emptyList()
        _isLoadingLiveData.value = true
        _isErrorLiveData.value = false

        Handler().postDelayed({
            if (Random.nextInt() % 10 == 0) {
                _contentLiveData.value = emptyList()
                _isLoadingLiveData.value = false
                _isErrorLiveData.value = true
            } else {
                val data  = repository.getPokemonList()
                _contentLiveData.postValue(data)
                _isLoadingLiveData.value = false
                _isErrorLiveData.value = false
            }
        }, 3000)
    }
}
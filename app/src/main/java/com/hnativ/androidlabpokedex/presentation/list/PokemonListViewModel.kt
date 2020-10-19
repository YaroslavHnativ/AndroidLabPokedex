package com.hnativ.androidlabpokedex.presentation.list

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hnativ.androidlabpokedex.data.PokemonRepositoryImpl
import com.hnativ.androidlabpokedex.domain.Pokemon
import com.hnativ.androidlabpokedex.domain.PokemonRepository
import kotlin.random.Random

class PokemonListViewModel : ViewModel() {
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
        showLoading()

        Handler().postDelayed({
            if (Random.nextInt() % 10 == 0) {
                showError()
            } else {
                repository.getPokemonList(object : PokemonRepository.ApiCallback<List<Pokemon>> {
                    override fun onSuccess(data: List<Pokemon>) {
                        showData(data)
                    }

                    override fun onError() {
                        showError()
                    }
                })
            }
        }, 3000)
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
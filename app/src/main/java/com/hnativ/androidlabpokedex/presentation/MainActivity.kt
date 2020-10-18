package com.hnativ.androidlabpokedex.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import android.view.View
import androidx.lifecycle.Observer
import com.hnativ.androidlabpokedex.R
import com.hnativ.androidlabpokedex.domain.Pokemon
import com.hnativ.androidlabpokedex.presentation.adapter.PokemonListAdapter
import com.hnativ.androidlabpokedex.presentation.adapter.PokemonListener
import com.hnativ.androidlabpokedex.presentation.details.PokemonDetailsActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val viewModel: PokemonViewModel by viewModels()
    private val adapter = PokemonListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.adapter = adapter

        adapter.clickListener = PokemonListener{id ->
            PokemonDetailsActivity.openDetails(this,id)
        }

        viewModel.isLoadingLiveData.observe(this, Observer {
            loadingView.visibility = if (it) {
                View.VISIBLE
            } else {
                View.GONE
            }
        })

        viewModel.isErrorLiveData.observe(this, Observer {
            errorView.visibility = if (it) {
                View.VISIBLE
            } else {
                View.GONE
            }
        })

        viewModel.contentLiveData.observe(this, Observer { data ->
            recyclerView.visibility = if (data.isNotEmpty()) {
                View.VISIBLE
            } else {
                View.GONE
            }
            setData(data)
        })

        viewModel.loadData()

    }


    private fun setData(data: List<Pokemon>) {
        adapter.submitList(data)
    }
}
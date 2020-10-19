package com.hnativ.androidlabpokedex.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hnativ.androidlabpokedex.R
import com.hnativ.androidlabpokedex.presentation.details.PokemonDetailsFragment
import com.hnativ.androidlabpokedex.presentation.list.PokemonListFragment

class MainActivity : AppCompatActivity(), Router {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openPokemonList()
    }

    override fun openPokemonList() {
        val fragment = PokemonListFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer,fragment,null)
            .commit()
    }

    override fun openPokemonDetails(id: String) {
        val fragment = PokemonDetailsFragment.newInstance(id)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer,fragment)
            .addToBackStack(null)
            .commit()
    }
}
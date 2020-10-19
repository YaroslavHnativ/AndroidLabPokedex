package com.hnativ.androidlabpokedex.presentation.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.hnativ.androidlabpokedex.R
import com.hnativ.androidlabpokedex.domain.PokemonDetails
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_pokemon_details.*

class PokemonDetailsFragment : Fragment(R.layout.fragment_pokemon_details) {
    private val viewModel: PokemonDetailsViewModel by viewModels()
    private val navArgs: PokemonDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.pokemonDetailsLiveData.observe(viewLifecycleOwner, { pokemonDetails ->
            if (pokemonDetails != null) {
                showPokemonDetails(pokemonDetails)
            }
        })

        viewModel.loadPokemonData(navArgs.id)
    }

    private fun showPokemonDetails(pokemonDetails: PokemonDetails) {
        name.text = pokemonDetails.name
        abilities.text = pokemonDetails.abilities.joinToString { it }
        Picasso.get()
            .load(pokemonDetails.imgUrl)
            .into(image)
    }
}
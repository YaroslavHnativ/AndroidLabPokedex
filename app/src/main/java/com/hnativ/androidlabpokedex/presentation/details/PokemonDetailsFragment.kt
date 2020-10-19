package com.hnativ.androidlabpokedex.presentation.details

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.hnativ.androidlabpokedex.R
import com.hnativ.androidlabpokedex.domain.PokemonDetails
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_pokemon_details.*

const val PARAM_POKEMON_ID = "param.pokemon.id"
class PokemonDetailsFragment : Fragment(R.layout.fragment_pokemon_details) {
    private val viewModel: PokemonDetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pokemonId = requireArguments().getString(PARAM_POKEMON_ID) ?: "1"

        viewModel.pokemonDetailsLiveData.observe(viewLifecycleOwner, { pokemonDetails ->
            if (pokemonDetails != null) {
                showPokemonDetails(pokemonDetails)
            }
        })

        viewModel.loadPokemonData(pokemonId)
    }

    private fun showPokemonDetails(pokemonDetails: PokemonDetails) {
        name.text = pokemonDetails.name
        abilities.text = pokemonDetails.abilities.joinToString { it }
        Picasso.get()
            .load(pokemonDetails.imgUrl)
            .into(image)
    }

    companion object {
        fun newInstance(id: String): PokemonDetailsFragment {
            val fragment = PokemonDetailsFragment()
            val bundle = bundleOf(
                PARAM_POKEMON_ID to id
            )
            fragment.arguments = bundle
            return fragment
        }
    }
}
package com.hnativ.androidlabpokedex.presentation.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.hnativ.androidlabpokedex.R
import com.hnativ.androidlabpokedex.domain.PokemonDetails
import com.hnativ.androidlabpokedex.presentation.MyViewModelFactory
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_pokemon_details.*

class PokemonDetailsFragment : Fragment(R.layout.fragment_pokemon_details) {
    private val viewModel: PokemonDetailsViewModel by viewModels {
        MyViewModelFactory()
    }
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

        Picasso.get()
            .load(pokemonDetails.imgUrl)
            .placeholder(R.drawable.loading_animation)
            .error(R.drawable.ic_broken_image)
            .into(image)

        weight.text = pokemonDetails.getWeightString()
        height.text = pokemonDetails.getHeightString()

        progressHp.labelText = pokemonDetails.getHpString()
        progressHp.max = PokemonDetails.maxHp.toFloat()
        progressHp.progress = pokemonDetails.hp.toFloat()

        progressAttack.labelText = pokemonDetails.getAttackString()
        progressAttack.max = PokemonDetails.maxAttack.toFloat()
        progressAttack.progress = pokemonDetails.attack.toFloat()

        progressDefense.labelText = pokemonDetails.getDefenseString()
        progressDefense.max = PokemonDetails.maxDefense.toFloat()
        progressDefense.progress = pokemonDetails.defense.toFloat()

        progressSpeed.labelText = pokemonDetails.getSpeedString()
        progressSpeed.max = PokemonDetails.maxSpeed.toFloat()
        progressSpeed.progress = pokemonDetails.speed.toFloat()

        progressExp.labelText = pokemonDetails.getExpString()
        progressExp.max = PokemonDetails.maxExp.toFloat()
        progressExp.progress = pokemonDetails.exp.toFloat()
    }
}
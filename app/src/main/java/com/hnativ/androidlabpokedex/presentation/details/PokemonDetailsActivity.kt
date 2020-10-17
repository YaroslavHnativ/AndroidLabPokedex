package com.hnativ.androidlabpokedex.presentation.details

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.hnativ.androidlabpokedex.R
import com.hnativ.androidlabpokedex.domain.PokemonDetails
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_pokemon_details.*
import java.security.AccessControlContext

const val PARAM_POKEMON_ID = "param.pokemon.id"

class PokemonDetailsActivity : AppCompatActivity() {
    private val viewModel by viewModels<PokemonDetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_details)

        val pokemonId = intent.getStringExtra(PARAM_POKEMON_ID) ?: "1"

        viewModel.pokemonDetailsLiveData.observe(this, { pokemonDetails ->
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
        fun openDetails(context: Context, id: String) {
            val intent = Intent(context, PokemonDetailsActivity::class.java)

            val bundle = Bundle()
            bundle.putString(PARAM_POKEMON_ID, id)
            intent.putExtras(bundle)

            context.startActivity(intent)
        }
    }
}
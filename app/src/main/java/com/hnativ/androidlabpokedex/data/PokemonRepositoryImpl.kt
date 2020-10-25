package com.hnativ.androidlabpokedex.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.hnativ.androidlabpokedex.domain.Pokemon
import com.hnativ.androidlabpokedex.domain.PokemonDetails
import com.hnativ.androidlabpokedex.domain.PokemonRepository
import com.hnativ.androidlabpokedex.persistance.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class PokemonRepositoryImpl(private val database: AppDatabase) : PokemonRepository {

    private val api: PokedexApiService = createPokedexApiService()

    override val pokemonList: LiveData<List<Pokemon>> = Transformations
        .map(database.pokemonDataDao().getPokemonList()) {
            it.asPokemon()
        }

    override suspend fun refreshPokemonData() {
        withContext(Dispatchers.IO) {
            val pokemonListResponse = api.fetchPokemonList()
            pokemonListResponse.results.map { pokemonPartialInfo ->
                runBlocking {
                    val pokemonInfoResponse = api.fetchPokemonInfo(pokemonPartialInfo.name)

                    val pokemonData = DatabasePokemonData(
                        pokemonInfoResponse.id,
                        pokemonInfoResponse.name,
                        pokemonInfoResponse.imageUrl,
                        pokemonInfoResponse.height,
                        pokemonInfoResponse.weight
                    )
                    database.pokemonDataDao().insertPokemonData(pokemonData)
                }
            }
        }
    }

    override suspend fun getPokemonById(id: String): PokemonDetails {
        lateinit var pokemonDetails: PokemonDetails
        withContext(Dispatchers.IO) {
            val pokemonData = database.pokemonDataDao().getPokemonById(id)
            pokemonDetails = pokemonData.asPokemonDetails()
        }
        return pokemonDetails
    }
}
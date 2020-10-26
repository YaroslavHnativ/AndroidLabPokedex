package com.hnativ.androidlabpokedex.persistance

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PokemonDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemonData(pokemonData: DatabasePokemonData)

    @Query("SELECT * FROM DatabasePokemonData")
    fun getPokemonList(): List<DatabasePokemonData>

    @Query("SELECT * FROM DatabasePokemonData WHERE id=:id_")
    fun getPokemonById(id_: String): DatabasePokemonData
}
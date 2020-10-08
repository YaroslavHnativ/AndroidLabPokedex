package com.hnativ.androidlabpokedex

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_pokemon.view.*

class PokemonListAdapter :
    ListAdapter<Pokemon, PokemonListAdapter.PokemonViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Pokemon>() {
        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem == newItem
        }

    }

    class PokemonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val name = view.name
        private val image = view.image

        fun bindTo(pokemon: Pokemon) {
            name.text = pokemon.name

            Picasso.get()
                .load(pokemon.imgUrl)
                .into(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PokemonViewHolder(
            layoutInflater.inflate(R.layout.item_pokemon, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

}
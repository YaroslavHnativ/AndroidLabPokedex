package com.hnativ.androidlabpokedex.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hnativ.androidlabpokedex.R
import com.hnativ.androidlabpokedex.domain.Pokemon
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_pokemon.view.*

class PokemonListAdapter :
    ListAdapter<Pokemon, PokemonListAdapter.PokemonViewHolder>(DiffCallback) {

    var clickListener: PokemonListener? = null

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

        fun bindTo(pokemon: Pokemon, clickListener: PokemonListener) {

            itemView.setOnClickListener {
                clickListener.onClick(pokemon)
            }

            name.text = pokemon.name

            Picasso.get()
                .load(pokemon.imgUrl)
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image)
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
        holder.bindTo(getItem(position), clickListener!!)
    }

}

class PokemonListener(val clickListener: (pokemonId: String) -> Unit) {
    fun onClick(pokemon: Pokemon) = clickListener(pokemon.id)
}
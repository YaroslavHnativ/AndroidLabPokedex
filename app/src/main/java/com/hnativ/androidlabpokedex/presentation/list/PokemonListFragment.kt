package com.hnativ.androidlabpokedex.presentation.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hnativ.androidlabpokedex.R
import com.hnativ.androidlabpokedex.domain.Pokemon
import com.hnativ.androidlabpokedex.presentation.MyViewModelFactory
import com.hnativ.androidlabpokedex.presentation.adapter.PokemonListAdapter
import com.hnativ.androidlabpokedex.presentation.adapter.PokemonListener
import kotlinx.android.synthetic.main.fragment_pokemon_list.*

class PokemonListFragment : Fragment(R.layout.fragment_pokemon_list) {
    private val listViewModel: PokemonListViewModel by viewModels {
        MyViewModelFactory()
    }
    private val adapter = PokemonListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.adapter = adapter

        adapter.clickListener = PokemonListener { id ->
            findNavController().navigate(
                PokemonListFragmentDirections
                    .actionPokemonListFragmentToPokemonDetailsFragment(id)
            )
        }

        listViewModel.isLoadingLiveData.observe(viewLifecycleOwner, {
            loadingView.visibility = if (it) {
                View.VISIBLE
            } else {
                View.GONE
            }
        })

        listViewModel.isErrorLiveData.observe(viewLifecycleOwner, {
            errorView.visibility = if (it) {
                View.VISIBLE
            } else {
                View.GONE
            }
        })

        listViewModel.contentLiveData.observe(viewLifecycleOwner, { data ->
            recyclerView.visibility = if (data.isNotEmpty()) {
                View.VISIBLE
            } else {
                View.GONE
            }
            setData(data)
        })
    }

    private fun setData(data: List<Pokemon>) {
        adapter.submitList(data)
    }
}
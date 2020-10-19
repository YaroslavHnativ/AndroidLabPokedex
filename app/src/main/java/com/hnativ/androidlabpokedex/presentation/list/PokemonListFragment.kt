package com.hnativ.androidlabpokedex.presentation.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.hnativ.androidlabpokedex.R
import com.hnativ.androidlabpokedex.domain.Pokemon
import com.hnativ.androidlabpokedex.presentation.adapter.PokemonListAdapter
import com.hnativ.androidlabpokedex.presentation.adapter.PokemonListener
import com.hnativ.androidlabpokedex.presentation.details.PokemonDetailsFragment
import kotlinx.android.synthetic.main.fragment_pokemon_list.*

class PokemonListFragment : Fragment(R.layout.fragment_pokemon_list) {
    private val listViewModel: PokemonListViewModel by viewModels()
    private val adapter = PokemonListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.adapter = adapter

        adapter.clickListener = PokemonListener { id ->
            val fragment = PokemonDetailsFragment.newInstance(id)

            requireActivity().supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer,fragment)
                .addToBackStack(null)
                .commit()
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

        listViewModel.loadData()
    }

    private fun setData(data: List<Pokemon>) {
        adapter.submitList(data)
    }
}
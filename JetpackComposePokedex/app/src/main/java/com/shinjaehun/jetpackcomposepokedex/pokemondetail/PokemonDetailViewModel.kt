package com.shinjaehun.jetpackcomposepokedex.pokemondetail

import androidx.lifecycle.ViewModel
import com.shinjaehun.jetpackcomposepokedex.data.remote.responses.Pokemon
import com.shinjaehun.jetpackcomposepokedex.repository.PokemonRepository
import com.shinjaehun.jetpackcomposepokedex.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    suspend fun getPokemonInfo(pokemonName: String) : Resource<Pokemon> {
        return repository.getPokemonInfo(pokemonName)
    }
}
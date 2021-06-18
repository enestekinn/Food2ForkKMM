package com.enestekin.food2forkkmm.presentation.recipe_list

import com.enestekin.food2forkkmm.domain.model.Recipe

data class RecipeListState(
    val isLoading: Boolean = false,
    val page: Int = 1,
    val query: String = "",
    val recipes: List<Recipe> = listOf(),
)
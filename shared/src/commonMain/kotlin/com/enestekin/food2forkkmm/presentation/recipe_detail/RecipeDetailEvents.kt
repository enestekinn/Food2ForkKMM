package com.enestekin.food2forkkmm.presentation.recipe_detail

import com.enestekin.food2forkkmm.presentation.recipe_list.RecipeListEvents

sealed class RecipeDetailEvents {
    data class GetRecipe (val recipeId: Int): RecipeDetailEvents()

    object OnRemoveHeadMessageFromQueue: RecipeDetailEvents()
}

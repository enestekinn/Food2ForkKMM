package com.enestekin.food2forkkmm.interactors.recipe_detail

import com.enestekin.food2forkkmm.datasource.cache.RecipeCache
import com.enestekin.food2forkkmm.domain.model.Recipe
import com.enestekin.food2forkkmm.domain.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetRecipe(
    private val recipeCache: RecipeCache,
) {
    fun execute(
        recipeId: Int,

    ):Flow<DataState<Recipe>> = flow {

        try {

            emit(DataState.loading())

            val recipe = recipeCache.get(recipeId)
            emit(DataState.data(message = null,data = recipe))

        }catch (e: Exception){
            emit(DataState.error<Recipe>(message = e.message?: "Unknown error"))
        }
    }

}
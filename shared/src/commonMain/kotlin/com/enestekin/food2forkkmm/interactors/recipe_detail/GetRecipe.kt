package com.enestekin.food2forkkmm.interactors.recipe_detail

import com.enestekin.food2forkkmm.datasource.cache.RecipeCache
import com.enestekin.food2forkkmm.domain.model.GenericMessageInfo
import com.enestekin.food2forkkmm.domain.model.Recipe
import com.enestekin.food2forkkmm.domain.model.UIComponentType
import com.enestekin.food2forkkmm.domain.util.CommonFlow
import com.enestekin.food2forkkmm.domain.util.DataState
import com.enestekin.food2forkkmm.domain.util.asCommonFlow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class GetRecipe (
    private val recipeCache: RecipeCache,
){
    fun execute(
        recipeId: Int,
    ): CommonFlow<DataState<Recipe>> = flow {
        try {
            emit(DataState.loading())

            if (recipeId == 1 || recipeId == 5 ) {

                throw Exception("Invalid Recipe Id:${recipeId}")

            }

            delay(2000)

            val recipe =  recipeCache.get(recipeId)

            emit(DataState.data(message = null, data = recipe))

        }catch (e: Exception){
            emit(DataState.error<Recipe>(

                message = GenericMessageInfo.Builder()
                    .id("GetRecipe.Error")
                    .title("Error")
                    .uiComponentType(UIComponentType.Dialog)
                    .description(e.message?: "Unknown Error")
            ))
        }
    }.asCommonFlow()

}
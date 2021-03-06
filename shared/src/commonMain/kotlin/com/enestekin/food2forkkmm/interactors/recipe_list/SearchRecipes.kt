package com.enestekin.food2forkkmm.interactors.recipe_list

import com.enestekin.food2forkkmm.datasource.cache.RecipeCache
import com.enestekin.food2forkkmm.datasource.network.RecipeService
import com.enestekin.food2forkkmm.domain.model.GenericMessageInfo
import com.enestekin.food2forkkmm.domain.model.Recipe
import com.enestekin.food2forkkmm.domain.model.UIComponentType
import com.enestekin.food2forkkmm.domain.util.CommonFlow
import com.enestekin.food2forkkmm.domain.util.DataState
import com.enestekin.food2forkkmm.domain.util.asCommonFlow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRecipes(
    private val recipeService: RecipeService,
    private val recipeCache: RecipeCache
) {
    fun execute(
        page:Int,
        query: String,
    ): CommonFlow<DataState<List<Recipe>>> = flow {
        emit(DataState.loading())

        try {
            val recipes = recipeService.search(
                page = page,
                query = query,
            )
              delay(500)

            if (query == "error"){
                throw Exception ("Forcing an error.. Search FAILED")
            }

            recipeCache.insert(recipes)

            val cacheResult = if (query.isBlank()){
                println("SearchRecipes: Query is Blank")

                recipeCache.getAll(page = page)

            }else{
                recipeCache.search(
                    query = query,
                    page = page
                )

            }

            // emit List<Recipe> from cache
            emit(DataState.data<List<Recipe>>(message = null, data = cacheResult))
        }catch (e: Exception){
            emit(DataState.error<List<Recipe>>(
                message = GenericMessageInfo.Builder()
                    .id("SearchRecipe.Error")
                    .title("Error")
                    .uiComponentType(UIComponentType.Dialog)
                    .description(e.message?: "Unknown Error")
            ))
        }
    }.asCommonFlow()



}
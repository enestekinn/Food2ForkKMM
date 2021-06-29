package com.enestekin.food2forkkmm.di

import com.enestekin.food2forkkmm.interactors.recipe_detail.GetRecipe

class GetRecipeModule(
    private val cacheModule: CacheModule,
) {

    val getRecipe: GetRecipe by lazy {
        GetRecipe(
            recipeCache = cacheModule.recipeCache
        )
    }


}
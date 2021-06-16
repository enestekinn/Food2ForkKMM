package com.enestekin.food2forkkmm.android.di

import com.enestekin.food2forkkmm.datasource.network.RecipeService
import com.enestekin.food2forkkmm.interactors.recipe_detail.GetRecipe
import com.enestekin.food2forkkmm.interactors.recipe_list.SearchRecipes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InteractorsModule {

    @Singleton
    @Provides
    fun providesSearchRecipes(
        recipeService: RecipeService,
    ): SearchRecipes {
        return SearchRecipes(recipeService = recipeService)
    }

    @Singleton
    @Provides
    fun providesGetRecipe(
        recipeService: RecipeService,
    ): GetRecipe {
        return GetRecipe(recipeService = recipeService)
    }
}
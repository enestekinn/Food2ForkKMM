package com.enestekin.food2forkkmm.presentation.recipe_list

import com.enestekin.food2forkkmm.domain.model.Recipe

sealed class RecipeListEvents {

    object LoadRecipes: RecipeListEvents()

    object NextPage: RecipeListEvents()

    object NewSearch: RecipeListEvents()

    data class OnUpdateQuery(val query:String): RecipeListEvents()

    data class OnSelectCategory(val category: FoodCategory): RecipeListEvents()

    object OnRemoveHeadMessageFromQueue: RecipeListEvents()
}

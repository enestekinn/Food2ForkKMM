package com.enestekin.food2forkkmm.android.presentation.recipe_list.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import com.enestekin.food2forkkmm.domain.model.Recipe

@Composable
fun RecipeList(
    loading: Boolean,
    recipes: List<Recipe>,
    onClickRecipeListItem:(Int) -> Unit
){
    if (loading && recipes.isEmpty()){
        //
    }else if(recipes.isEmpty()) {
        //Nothing to show... No repices

    }else {
        LazyColumn {
            itemsIndexed(
                items = recipes,
            ){index, recipe ->
                RecipeCard(
                    recipe = recipe,
                    onClick = {onClickRecipeListItem(recipe.id)
                    }
                )


            }

        }
    }

}
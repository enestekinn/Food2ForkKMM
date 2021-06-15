package com.enestekin.food2forkkmm.android.presentation.recipe_detail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.enestekin.food2forkkmm.domain.model.Recipe

@Composable
fun RecipeDetailScreen(
    recipe: Recipe?
){
    if (recipe == null) {
        Text("ERROR")
    }else {
        Text("RecipeDetailScreen: ${recipe.title}")
    }
}
package com.enestekin.food2forkkmm.android.presentation.recipe_detail

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import com.enestekin.food2forkkmm.android.presentation.theme.AppTheme
import com.enestekin.food2forkkmm.domain.model.Recipe

@ExperimentalMaterialApi
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RecipeDetailScreen(
    recipe: Recipe?
){

    AppTheme(displayProgressBar = false) {
        if (recipe == null) {
            Text("ERROR")
        }else {
            Text("RecipeDetailScreen: ${recipe.title}")
        }
    }

}
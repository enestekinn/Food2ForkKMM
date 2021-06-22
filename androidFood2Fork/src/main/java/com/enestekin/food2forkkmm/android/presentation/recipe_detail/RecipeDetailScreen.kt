package com.enestekin.food2forkkmm.android.presentation.recipe_detail

import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.enestekin.food2forkkmm.android.presentation.recipe_detail.components.RecipeView
import com.enestekin.food2forkkmm.android.presentation.theme.AppTheme
import com.enestekin.food2forkkmm.presentation.recipe_detail.RecipeDetailEvents
import com.enestekin.food2forkkmm.presentation.recipe_detail.RecipeDetailState
import kotlin.reflect.KFunction1

@ExperimentalStdlibApi
@ExperimentalMaterialApi
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RecipeDetailScreen(
    state: RecipeDetailState,
    onTriggerEvent: (RecipeDetailEvents) -> Unit
){

    AppTheme(
        displayProgressBar = state.isLoading) {
        if (state.recipe == null && state.isLoading) {
            //Loading
        }else if (state.recipe == null) {
            Text(
                text = "We were unable to retrieve the details for this recipe \n Try resetting the app",
            modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.body1
            )
         RecipeView(recipe = state.recipe!!)
        }
    }

}
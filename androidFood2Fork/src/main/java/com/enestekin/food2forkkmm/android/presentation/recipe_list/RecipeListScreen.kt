package com.enestekin.food2forkkmm.android.presentation.recipe_list

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import com.enestekin.food2forkkmm.android.presentation.recipe_list.components.RecipeList
import com.enestekin.food2forkkmm.android.presentation.recipe_list.components.SearchAppBar
import com.enestekin.food2forkkmm.android.presentation.theme.AppTheme
import com.enestekin.food2forkkmm.presentation.recipe_list.RecipeListEvents
import com.enestekin.food2forkkmm.presentation.recipe_list.RecipeListState

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun RecipeListScreen(
    state: RecipeListState,
     onTriggerEvent: (RecipeListEvents) -> Unit,
    onClickRecipeListItem: (Int) -> Unit
){

    AppTheme(displayProgressBar = state.isLoading) {


    Scaffold(
        topBar = {
                 SearchAppBar(query = state.query, onQueryChange = {
                 onTriggerEvent(RecipeListEvents.OnUpdateQuery(it))
                  },
                 onExecuteSearch = {
                    onTriggerEvent(RecipeListEvents.NewSearch)
                 })
        },
    ) {
        RecipeList(
            loading = state.isLoading,
            recipes = state.recipes,
            page = state.page,
            onTriggerNextPage = {
                onTriggerEvent(RecipeListEvents.NextPage)
            },
            onClickRecipeListItem =onClickRecipeListItem
        )
    }

        }
    }


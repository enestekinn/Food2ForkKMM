package com.enestekin.food2forkkmm.android.presentation.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.enestekin.food2forkkmm.android.presentation.recipe_detail.RecipeDetailScreen
import com.enestekin.food2forkkmm.android.presentation.recipe_detail.RecipeDetailViewModel
import com.enestekin.food2forkkmm.android.presentation.recipe_list.RecipeListScreen
import com.enestekin.food2forkkmm.android.presentation.recipe_list.RecipeListViewModel

@ExperimentalStdlibApi
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.RecipeList.route) {
        composable(route = Screen.RecipeList.route){navBackStackEntry ->
            val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
            val viewModel: RecipeListViewModel = viewModel("RecipeListViewModel",factory)
           RecipeListScreen(onSelectRecipe = { recipeId ->
               navController.navigate(Screen.RecipeDetail.route + "/$recipeId")
           })
        }
        composable(
            route = Screen.RecipeDetail.route +"/{recipeId}",
            arguments = listOf(navArgument("recipeId"){
                type = NavType.IntType
            })
        ){navBackStackEntry ->
            val factory = HiltViewModelFactory(LocalContext.current,navBackStackEntry)
            val viewModel: RecipeDetailViewModel = viewModel("RecipeDetailViewModel",factory)
            RecipeDetailScreen(
                recipe = viewModel.recipe.value
            )


        }
    }
}
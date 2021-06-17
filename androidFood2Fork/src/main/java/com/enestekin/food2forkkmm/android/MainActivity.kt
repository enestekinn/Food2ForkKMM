package com.enestekin.food2forkkmm.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import com.enestekin.food2forkkmm.android.presentation.navigation.Navigation
import com.enestekin.food2forkkmm.datasource.network.KtorClientFactory
import com.enestekin.food2forkkmm.datasource.network.RecipeServiceImpl
import com.enestekin.food2forkkmm.datasource.network.model.RecipeDto
import com.enestekin.food2forkkmm.datasource.network.toRecipe
import com.enestekin.food2forkkmm.domain.util.DatetimeUtil
import dagger.hilt.android.AndroidEntryPoint
import io.ktor.client.request.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch


const val TOKEN = "Token 9c8b06d329136da358c2d00e76946b0111ce2c48"
const val BASE_URL = "https://food2fork.ca/api/recipe"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @ExperimentalMaterialApi
    @ExperimentalComposeUiApi
    @ExperimentalStdlibApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val ktorClient = KtorClientFactory().build()
        CoroutineScope(IO).launch {

            val recipeService = RecipeServiceImpl(
                httpClient = ktorClient,
                baseUrl = BASE_URL
            )
            val recipeId = 250
            val recipe = recipeService.get(recipeId)


            println("KtorTest: ${recipe.title}")
            println("KtorTest: ${recipe.ingredients}")
            println("KtorTest: ${recipe.dateUpdated}")

            println("KtorTest: ${DatetimeUtil().humanizeDatetime(recipe.dateUpdated)}")
        }


        setContent {
            Navigation()
        }


    }
}

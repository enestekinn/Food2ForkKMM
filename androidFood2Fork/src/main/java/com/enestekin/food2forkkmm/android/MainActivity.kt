package com.enestekin.food2forkkmm.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import com.enestekin.food2forkkmm.android.presentation.navigation.Navigation
import com.enestekin.food2forkkmm.datasource.network.KtorClientFactory
import dagger.hilt.android.AndroidEntryPoint
import io.ktor.client.request.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch


const val TOKEN = "Token 9c8b06d329136da358c2d00e76946b0111ce2c48"
const val BASE_URL = "https://food2fork.ca/api/recipe"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val ktorClient = KtorClientFactory().build()
        CoroutineScope(IO).launch {
            val recipeId = 1551
            val recipe = ktorClient.get<String>{
                url("$BASE_URL/get?id=$recipeId")
                header("Authorization", TOKEN)
            }
            println("KtorTest: ${recipe}")
        }


        setContent {
            Navigation()
        }


    }
}

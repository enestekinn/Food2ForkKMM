package com.enestekin.food2forkkmm.datasource.network

import com.enestekin.food2forkkmm.datasource.domain.model.Recipe
import com.enestekin.food2forkkmm.datasource.network.model.RecipeDto
import io.ktor.client.*

expect class KtorClientFactory(){
    fun build(): HttpClient
}

fun RecipeDto.toRecipe(): Recipe {
    return Recipe(
        id = pk,
        title = title,
        featuredImage = featuredImage,
        rating = rating,
        publisher = publisher,
        sourceUrl = sourcelUrl,
        ingredients = ingredients,
        dateAdded = ,//?
        dateUpdated = ,//?
    )
}
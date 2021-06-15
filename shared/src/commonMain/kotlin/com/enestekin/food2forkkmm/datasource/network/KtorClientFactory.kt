package com.enestekin.food2forkkmm.datasource.network

import com.enestekin.food2forkkmm.domain.model.Recipe
import com.enestekin.food2forkkmm.datasource.network.model.RecipeDto
import com.enestekin.food2forkkmm.domain.util.DatetimeUtil
import io.ktor.client.*

expect class KtorClientFactory(){
    fun build(): HttpClient
}

fun RecipeDto.toRecipe(): Recipe {

    val datetimeUtil = DatetimeUtil()
    return Recipe(
        id = pk,
        title = title,
        featuredImage = featuredImage,
        rating = rating,
        publisher = publisher,
        sourceUrl = sourcelUrl,
        ingredients = ingredients,
        dateAdded = datetimeUtil.toLocalDate(longDateAdded.toDouble()),
        dateUpdated =datetimeUtil.toLocalDate(longDateUpdated.toDouble())
    )
}

fun List<RecipeDto>.toRecipeList():  List<Recipe>{
    return  map { it.toRecipe()}
}
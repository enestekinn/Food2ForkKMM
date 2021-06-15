package com.enestekin.food2forkkmm.datasource.network.model

import kotlinx.serialization.SerialName

data class RecipeSearchResponse(

    @SerialName("count")
    var count: Int,

    @SerialName("results")
    var results: List<RecipeDto>
)


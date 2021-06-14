package com.enestekin.food2forkkmm.datasource.domain.model

import kotlinx.datetime.LocalDateTime

data class Recipe(
    val id: Int,
    val title: String,
    val publisher: String,
    val featured_image: String,
    val rating: Int,
    val source_url: String,
    val ingredients: List<String> = listOf(),
    val dateAdded: LocalDateTime,
    val dateUpdated: LocalDateTime,
)
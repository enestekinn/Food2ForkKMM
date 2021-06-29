package com.enestekin.food2forkkmm.presentation.recipe_list

import com.enestekin.food2forkkmm.domain.model.GenericMessageInfo
import com.enestekin.food2forkkmm.domain.model.Recipe
import com.enestekin.food2forkkmm.domain.util.Queue

expect class RecipeListState

const val RECIPE_PAGINATION_PAGE_SIZE = 30
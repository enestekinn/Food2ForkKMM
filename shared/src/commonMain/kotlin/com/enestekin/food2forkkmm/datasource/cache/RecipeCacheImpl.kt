package com.enestekin.food2forkkmm.datasource.cache

import com.enestekin.food2forkkmm.datasource.network.RecipeServiceImpl.Companion.RECIPE_PAGINATION_PAGE_SIZE
import com.enestekin.food2forkkmm.domain.model.Recipe
import com.enestekin.food2forkkmm.domain.util.DatetimeUtil

class RecipeCacheImpl(
    private val recipeDatabase: RecipeDatabase,
    private val datetimeUtil: DatetimeUtil
): RecipeCache {

    private val queries: RecipeDbQueries = recipeDatabase.recipeDbQueries


    override fun insert(recipe: Recipe) {
        queries.insertRecipe(
            id = recipe.id.toLong(),
            title = recipe.title,
            publisher = recipe.publisher,
            featured_image = recipe.featuredImage,
            rating = recipe.rating.toLong(),
            source_url = recipe.sourceUrl,
            ingredients =  , //TODO("convert String to List")
        date_updated = datetimeUtil.toEpochMilliseconds(recipe.dateUpdated),
            date_added = datetimeUtil.toEpochMilliseconds(recipe.dateAdded),
        )
    }

    override fun insert(recipes: List<Recipe>) {
       for (recipe in recipes) {
           insert(recipe)
       }
    }

    override fun search(query: String, page: Int): List<Recipe> {
     return queries.searchRecipes(
         query = query,
         pageSize = RECIPE_PAGINATION_PAGE_SIZE.toLong(),
         offset = RECIPE_PAGINATION_PAGE_SIZE * (page-1)
     ).executeAsList() //TODO("convert List<Recipe_Entity to List<Recipe>)
    }

    override fun getAll(page: Int): List<Recipe> {
        return queries.searchRecipes(
            pageSize = RECIPE_PAGINATION_PAGE_SIZE.toLong(),
            offset = RECIPE_PAGINATION_PAGE_SIZE * (page-1)
        ).executeAsList() //TODO("convert List<Recipe_Entity to List<Recipe>)
    }

    override fun get(recipeId: Int): Recipe? {
        return try {
            queries
                .getRecipeById(id = recipeId.toLong())
                .executeAsOne() //TODO("convert Recipe_Entity to Recipe)

        }catch (e: NullPointerException){
            null
        }
    }
}
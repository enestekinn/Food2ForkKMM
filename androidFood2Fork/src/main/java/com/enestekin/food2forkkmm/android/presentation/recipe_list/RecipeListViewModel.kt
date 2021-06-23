package com.enestekin.food2forkkmm.android.presentation.recipe_list

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enestekin.food2forkkmm.domain.model.GenericMessageInfo
import com.enestekin.food2forkkmm.domain.model.Recipe
import com.enestekin.food2forkkmm.domain.model.UIComponentType
import com.enestekin.food2forkkmm.domain.util.GenericMessageInfoQueueUtil
import com.enestekin.food2forkkmm.interactors.recipe_list.SearchRecipes
import com.enestekin.food2forkkmm.presentation.recipe_list.FoodCategory
import com.enestekin.food2forkkmm.presentation.recipe_list.RecipeListEvents
import com.enestekin.food2forkkmm.presentation.recipe_list.RecipeListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class RecipeListViewModel
@Inject constructor(
    private val savedStateHandle: SavedStateHandle, // dont need for this VM
private val searchRecipes: SearchRecipes,
): ViewModel(){

    val state: MutableState<RecipeListState> = mutableStateOf(RecipeListState())

    init {
        onTriggerEvent(RecipeListEvents.LoadRecipes)

    }

    fun onTriggerEvent(event: RecipeListEvents){
        when (event) {
            RecipeListEvents.LoadRecipes -> {
                loadRecipes()
            }
            RecipeListEvents.NewSearch -> {
                newSearch()
            }
            RecipeListEvents.NextPage -> {
                nextPage()
            }
            is RecipeListEvents.OnUpdateQuery -> {
                state.value = state.value.copy(query = event.query, selectedCategory = null)
            }
            is RecipeListEvents.OnSelectCategory -> {
                onSelectCategory(event.category)
            }
            else -> {
                appendToMessageQueue(
                    GenericMessageInfo.Builder()
                        .id(UUID.randomUUID().toString())
                        .title("Error")
                        .uiComponentType(UIComponentType.Dialog)
                        .description( "Invalid Event")
                )
            }
        }
    }

    private fun onSelectCategory(category: FoodCategory){
        state.value = state.value.copy(selectedCategory = category,query = category.value)
        Log.d("AppDebug", "onSelectCategory: ${category.value} ")
        newSearch()
    }
    private fun newSearch() {
        state.value = state.value.copy(page = 1,recipes = listOf())
        loadRecipes()
    }

    private fun nextPage() {
        state.value = state.value.copy(page = state.value.page + 1)
        loadRecipes()
    }
    private fun loadRecipes() {
        searchRecipes.execute(
            page = state.value.page,
            query = state.value.query
        ).onEach { dataState ->

            state.value =state.value.copy(isLoading = dataState.isLoading)

            dataState.data?.let { recipes->
               appendRecipes(recipes)

            }
            dataState.message?.let { message ->
appendToMessageQueue(message)
            }

        }.launchIn(viewModelScope)
    }


    private fun appendRecipes(recipes: List<Recipe>){
        val curr = ArrayList(state.value.recipes)
        curr.addAll(recipes)
        state.value = state.value.copy(recipes = curr)

    }
    private fun appendToMessageQueue(messageInfo: GenericMessageInfo.Builder){

        if (GenericMessageInfoQueueUtil().doesMessageAlreadyExistInQueue(
                queue = state.value.queue,
                messageInfo = messageInfo.build()
        )){
            val queue =state.value.queue
            queue.add(messageInfo.build())
            state.value = state.value.copy(queue = queue)
        }

    }
}
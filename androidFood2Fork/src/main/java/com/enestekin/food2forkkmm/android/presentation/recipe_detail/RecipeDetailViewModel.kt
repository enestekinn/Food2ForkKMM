package com.enestekin.food2forkkmm.android.presentation.recipe_detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enestekin.food2forkkmm.datasource.network.RecipeService
import com.enestekin.food2forkkmm.domain.model.GenericMessageInfo
import com.enestekin.food2forkkmm.domain.model.Recipe
import com.enestekin.food2forkkmm.domain.model.UIComponentType
import com.enestekin.food2forkkmm.domain.util.DatetimeUtil
import com.enestekin.food2forkkmm.domain.util.GenericMessageInfoQueueUtil
import com.enestekin.food2forkkmm.interactors.recipe_detail.GetRecipe
import com.enestekin.food2forkkmm.presentation.recipe_detail.RecipeDetailEvents
import com.enestekin.food2forkkmm.presentation.recipe_detail.RecipeDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject


@ExperimentalStdlibApi
@HiltViewModel
class RecipeDetailViewModel
@Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getRecipe: GetRecipe
): ViewModel(){

    val state: MutableState<RecipeDetailState> = mutableStateOf(RecipeDetailState())

    init {
        savedStateHandle.get<Int>("recipeId")?.let {recipeId ->

          onTriggerEvent(RecipeDetailEvents.GetRecipe(recipeId))


        }
    }

    fun onTriggerEvent(event: RecipeDetailEvents){
        when(event){
            is RecipeDetailEvents.GetRecipe -> {
                getRecipe(event.recipeId)
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

    private fun getRecipe(recipeId: Int) {
        getRecipe.execute(recipeId = recipeId).onEach { dataState ->


        state.value = state.value.copy(isLoading = dataState.isLoading)


    println(dataState.data)
            dataState.data?.let { recipe ->
                println("RecipeDetailVM:  Tekin")

                println("RecipeDetailVM: recipe: ${recipe}")
                state.value = state.value.copy(recipe = recipe)
            }
            dataState.message?.let { message ->
           appendToMessageQueue(message)
            }
        }.launchIn(viewModelScope)
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
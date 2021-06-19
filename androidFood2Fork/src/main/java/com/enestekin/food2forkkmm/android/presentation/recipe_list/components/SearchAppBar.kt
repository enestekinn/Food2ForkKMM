package com.enestekin.food2forkkmm.android.presentation.recipe_list.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@ExperimentalComposeUiApi
@Composable
fun SearchAppBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onExecuteSearch:() -> Unit,
){
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = MaterialTheme.colors.secondary,
        elevation = 8.dp
    ) {
        val keyboardController = LocalSoftwareKeyboardController.current

        Column {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                TextField(
                    value = query,
                    onValueChange = onQueryChange,
                    label = {
                        Text(text ="Search...")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            onExecuteSearch()
                            keyboardController?.hide()

                        }
                    ),
                    leadingIcon = {
                        Icon(Icons.Filled.Search,contentDescription = "Search Icon")
                    },
                    textStyle = TextStyle(color = MaterialTheme.colors.onSurface),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.surface
                    )
                )

            }
        }

    }
}
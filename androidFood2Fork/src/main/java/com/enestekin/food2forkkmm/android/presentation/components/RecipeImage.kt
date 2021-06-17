package com.enestekin.food2forkkmm.android.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.imageloading.ImageLoadState
import io.ktor.client.request.*

const val RECIPE_IMAGE_HEIGHT =  260
@Composable
fun RecipeImage(
    url: String,
    contentDescription: String,
){

    val painter = rememberCoilPainter(request = url)
    Box {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(RECIPE_IMAGE_HEIGHT.dp),
            painter = painter,
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop
        )
    }

    when(painter.loadState){
        is ImageLoadState.Error ->{
 //show error
        }
        is ImageLoadState.Success -> {
            // do something on success?

        }
        is ImageLoadState.Loading -> {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(RECIPE_IMAGE_HEIGHT.dp)) {
                //empty for white back ground

            }
        }
    }

}
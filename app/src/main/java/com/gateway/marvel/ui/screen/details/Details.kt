package com.gateway.marvel.ui.screen.details

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.gateway.marvel.data.domain.model.*


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.N)
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun Details(
    vm: DetailsViewModel = hiltViewModel(),
    navController: NavHostController
) {


    val state = vm.uiState.value


    val selectedCategory = vm.selectedCategory

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)

                    }
                },
                title = { Text(text = selectedCategory) }
            )
        }
    ) {

        state.data?.marvelData?.let { it1 -> MarvelData(it1) }
    }
}



@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun MarvelData(characters: List<MarvelData>) {

    DetailsVerticalGrid(data = characters) {
        val url = "${it.thumbnail?.path}.${it.thumbnail?.extension}"
        DetailsGridItemCard(image = url, title = it.title!!) {

        }
    }

}


@RequiresApi(Build.VERSION_CODES.N)
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun <T> DetailsVerticalGrid(
    modifier: Modifier = Modifier,
    data: List<T>,
    itemContent: @Composable (T) -> Unit,
) {

    LazyVerticalGrid(
        columns = GridCells.Adaptive(30.dp),
        modifier = modifier
    ) {
        items(data.size) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                data.forEach {
                    itemContent(it)
                }
            }
        }
    }


}


@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun DetailsGridItemCard(
    image: String,
    title: String,
    onItemClicked: () -> Unit
) {


    Surface(
        onClick = onItemClicked,
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.size(200.dp)
    ) {
        Column {

            AsyncImage(model = image, contentDescription = null)

            Image(
                painter = painterResource(id = image as Int),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )

            Text(text = title, textAlign = TextAlign.Start)
        }
    }

}









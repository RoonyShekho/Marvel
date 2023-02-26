package com.gateway.marvel.ui.screen.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.gateway.marvel.R
import com.gateway.marvel.data.domain.model.Characters
import com.gateway.marvel.data.utility.checkIfOnline
import com.skydoves.landscapist.coil.CoilImage


@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun Details(
    vm: DetailsViewModel = hiltViewModel(),
    navController: NavHostController
) {


    DetailsContent(vm = vm) {
        navController.popBackStack()
    }


}


@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun DetailsContent(vm: DetailsViewModel, onBackPressed: () -> Unit) {


    val context = LocalContext.current

    val state = vm.uiState


    val selectedCategory = vm.selectedCategory


    if (!checkIfOnline(context)) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(text = "No internet connection!")
        }
    } else {

        Scaffold(
            topBar = {
                TopAppBar(
                    navigationIcon = {
                        IconButton(onClick = {
                            onBackPressed()
                        }) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                        }
                    },
                    title = { Text(text = selectedCategory) }
                )
            }
        ) { paddingValues ->
            state.marvelData?.let {
                MarvelData(it, paddingValues = paddingValues)
            }
        }
    }


}

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun MarvelData(characters: List<Characters>, paddingValues: PaddingValues) {


    DetailsVerticalGrid(
        data = characters, itemContent = {
            val imageUrl = "${it.thumbnail?.path}.${it.thumbnail?.extension}"
            DetailsGridItemCard(image = imageUrl, title = it.name)
        },
        modifier = Modifier.padding(paddingValues)
    )
}


@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun <T> DetailsVerticalGrid(
    modifier: Modifier = Modifier,
    data: List<T>,
    itemContent: @Composable (T) -> Unit,
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(data) {
            itemContent(it)
        }
    }
}


@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun DetailsGridItemCard(
    image: String,
    title: String
) {


    Surface(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.size(200.dp),
        elevation = 12.dp
    ) {
        Column {
            Text(text = title, textAlign = TextAlign.Start)

            CoilImage(imageModel = { image }, failure = {
                Image(
                    painter = painterResource(R.drawable.ic_launcher_background),
                    contentDescription = null
                )
            })
        }
    }

}









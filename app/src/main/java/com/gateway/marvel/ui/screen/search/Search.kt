package com.gateway.marvel.ui.screen.search

import androidx.compose.foundation.lazy.LazyRow


import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.gateway.marvel.data.domain.model.MarvelData
import com.gateway.marvel.ui.navigation.Screen
import com.gateway.marvel.ui.screen.common.SearchBar

@Composable
fun Search(
    vm: SearchViewModel = hiltViewModel(),
    navController: NavHostController
) {

    val query by vm.query

    val state = vm.state

    var onTrailingClicked by remember { mutableStateOf(false) }

    Log.d("SearchScreen", state.toString())

    LaunchedEffect(Unit) {
        if (onTrailingClicked) {
            if (query.isEmpty()) {
                navController.navigate(Screen.Home.route)
            } else {
                vm.setQuery("")
            }

        }
    }

    Scaffold(
        topBar = {
            SearchBar(
                readOnly = false,
                onValueChange = {
                    vm.setQuery(it)
                },
                value = query,
                trailingIcon = Icons.Default.Close,
                onTrailingButtonClick = {
                    onTrailingClicked = true
                },
                enabled = true,
                modifier = Modifier.fillMaxWidth()
            )
            {

            }
        }
    ) { paddingValues ->

        LazyColumn(
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            item {
                ItemCard(modifier = Modifier.padding(paddingValues), marvelData = state!!)
            }

        }


    }
}


@Composable
fun ItemCard(
    marvelData: MarvelData,
    modifier: Modifier = Modifier
) {

    Surface(
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
    ) {

        Row {

            val model = "${marvelData.thumbnail?.path}.${marvelData.thumbnail?.extension}"

            AsyncImage(model = model, contentDescription = null, modifier = Modifier.size(72.dp))

            Column {
                Text(text = marvelData.title!!, fontWeight = FontWeight.Bold)

                Text(text = marvelData.description!!)
            }


        }
    }

}


@Composable
fun CategoriesColumnView(
    marvelData: MarvelData,
    modifier: Modifier = Modifier,
    navigateToDetails: (String) -> Unit,
    comics: List<MarvelData>
) {


    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Characters")

            LazyRow(modifier = Modifier.padding(horizontal = 16.dp)) {
                items(comics) {
                    ItemCard(marvelData = it)
                }
            }


            Text(text = "Comics")
            LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
                items(marvelData.comics.items) {
                    Text(
                        text = it.title!!,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )


                    Text(text = "Stories")

                    LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
                        items(marvelData.stories.items) {

                            ItemCard(marvelData)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun ItemCard(
    marvelData: MarvelData
) {

    Surface(shape = RoundedCornerShape(12.dp)) {
        Column {
            Box(modifier = Modifier.fillMaxWidth()) {
                val model = "${marvelData.thumbnail?.path}.${marvelData.thumbnail?.extension}"
                AsyncImage(model = model, contentDescription = null)
            }

            Text(text = marvelData.title!!)
        }
    }

}
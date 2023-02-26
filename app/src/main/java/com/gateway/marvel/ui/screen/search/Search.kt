package com.gateway.marvel.ui.screen.search


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.gateway.marvel.data.domain.model.Characters
import com.gateway.marvel.ui.navigation.Screen
import com.gateway.marvel.ui.screen.common.SearchBar
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun Search(
    vm: SearchViewModel = hiltViewModel(),
    navController: NavHostController
) {

    val query by vm.query

    val state = vm.state


    var onTrailingClicked by remember { mutableStateOf(false) }

    val emptyResponseMessage = vm.emptyResponseMessage

    LaunchedEffect(onTrailingClicked) {
        if (onTrailingClicked) {
            if (query.isBlank()) {
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
                state.marvelData?.let {
                    if (it.isEmpty()) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = emptyResponseMessage)
                        }
                    } else {
                        ItemCard(
                            modifier = Modifier.padding(paddingValues),
                            characters = state.marvelData[0]
                        )

                    }
                }
            }

        }

    }
}


@Composable
fun ItemCard(
    characters: Characters,
    modifier: Modifier = Modifier
) {

    Surface(
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
    ) {

        val model = "${characters.thumbnail?.path}.${characters.thumbnail?.extension}"
        Row {
            CoilImage(
                imageModel = { model },
                modifier = Modifier.size(72.dp)
            )

            Column {
                Text(text = characters.name, fontWeight = FontWeight.Bold)

                Text(text = characters.description!!)
            }


        }
    }

}

/*
@Composable
fun CategoriesColumnView(
    modifier: Modifier = Modifier,
    marvelData:MarvelState
) {


    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Series")

            LazyRow(modifier = Modifier.padding(horizontal = 16.dp)) {
                items(marvelData.series.items) {
                    Text(text = it.name)
                }
            }


            Text(text = "Comics")
            LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
                items(marvelData.comics.items) {
                    Text(
                        text = it.name,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )

                    Text(text = "Stories")

                    LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
                        items(marvelData.stories.items) {
                            Text(text = it.name)
                        }
                    }
                }
            }
        }
    }
}*/

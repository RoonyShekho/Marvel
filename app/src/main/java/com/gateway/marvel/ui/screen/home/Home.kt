package com.gateway.marvel.ui.screen.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.gateway.marvel.R
import com.gateway.marvel.ui.navigation.Screen
import com.gateway.marvel.ui.screen.common.GridItemCard
import com.gateway.marvel.ui.screen.common.SearchBar
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState


val gridsData = listOf(
    GridsData("Characters", R.drawable.marvel_characters),
    GridsData("Comics", R.drawable.marvel_comics),
    GridsData("Events", R.drawable.event),
    GridsData("Cartoons", R.drawable.marvel_cartoons),
    GridsData("Series", R.drawable.marvel_stories),
    GridsData("Stories", R.drawable.marvel_stories)
)

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalPagerApi
@Composable
fun Home(
    navController: NavHostController
) {


    HomeContent(
        navigateToSearch = {
            navController.navigate(Screen.Search.route)
        },
        navigateToDetails = {
            navController.navigate(Screen.Details.getCategory(it))
        },
        modifier = Modifier.fillMaxSize()
    )
}


@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalComposeUiApi
@Composable
fun HomeContent(
    navigateToSearch: () -> Unit,
    navigateToDetails: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            AppBar()
        }
    ) { paddingValues ->

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Top
        ) {
            HorizontalPager(modifier = Modifier.padding(12.dp))

            SearchBar(
                leadingIcon = Icons.Default.Search,
                readOnly = true,
                value = "",
                onValueChange = {},
                onTrailingButtonClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                enabled = false
            ) {
                navigateToSearch()
            }

            Spacer(modifier = Modifier.height(10.dp))

            VerticalGrid(
                selectedCard = {
                    navigateToDetails(it)
                },
                data = gridsData,
                modifier = modifier.weight(1f)
            )
        }
    }
}

@Composable
fun AppBar(
    logo: Int = R.drawable.marvel_logo
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = logo),
            contentDescription = null,
            modifier = Modifier.size(120.dp)
        )
    }
}


@ExperimentalComposeUiApi
@ExperimentalPagerApi
@Composable
fun HorizontalPager(modifier: Modifier = Modifier) {

    val pagerState = rememberPagerState()

    val images = listOf(
        R.drawable.comics,
        R.drawable.captain_america,
        R.drawable.hulk
    )


    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        HorizontalPager(
            count = images.size,
            state = pagerState
        ) { currentPage ->
            Surface(
                shape = RoundedCornerShape(20.dp)
                ) {
                Image(
                    painter = painterResource(id = images[currentPage]),
                    contentDescription = null
                )
            }
        }

        HorizontalPagerIndicator(pagerState = pagerState)
    }
}

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun VerticalGrid(
    data: List<GridsData>,
    selectedCard: (String) -> Unit,
    modifier: Modifier = Modifier
) {


    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier,
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(data.size) { index ->
            GridItemCard(image = data[index].image, title = data[index].title, onItemClicked = {
                selectedCard(data[index].title)
            })
        }
    }
}


data class GridsData(
    val title: String,
    val image: Int
)


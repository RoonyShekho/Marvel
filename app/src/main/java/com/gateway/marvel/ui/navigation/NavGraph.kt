package com.gateway.marvel.ui.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.gateway.marvel.ui.screen.details.Details
import com.gateway.marvel.ui.screen.home.Home
import com.gateway.marvel.ui.screen.search.Search
import com.google.accompanist.pager.ExperimentalPagerApi


sealed class Screen(val route: String) {
    object Home : Screen("home_screen")
    object Details : Screen("details_screen/{category}") {
        fun getCategory(category: String): String {
            return "details_screen/$category"
        }
    }

    object Search : Screen("search_screen")
}

@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalComposeUiApi
@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(navController, startDestination = Screen.Home.route) {

        composable(route = Screen.Home.route) {
            Home(navController = navController)
        }


        composable(
            route = Screen.Details.route,
            arguments = listOf(
                navArgument("category") {
                    type = NavType.StringType
                }
            )
        ) {
            Details(navController = navController)
        }


        composable(route = Screen.Search.route) {
            Search(navController = navController)
        }
    }


}
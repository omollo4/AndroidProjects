package com.adyo.restaurant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.adyo.restaurant.ui.theme.RestaurantScreen
import com.adyo.restaurant.ui.theme.RestaurantTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RestaurantTheme {
                RestaurantApp()
            }
        }
    }
}

@Composable
fun RestaurantApp(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "restaurants") {
        composable(route = "restaurants"){
            RestaurantScreen{id ->
            navController.navigate("restaurants/$id")}
        }
        composable(
            route = "restaurants/{restaurant_id}",
            arguments = listOf(navArgument("restaurant_id"){
            type = NavType.IntType
        }),
            deepLinks = listOf(navDeepLink { uriPattern =
                "www.restaurantsapp.details.com/{restaurant_id}" }
        )) {
            RestaurantDetailsScreen()
        }
    }
}


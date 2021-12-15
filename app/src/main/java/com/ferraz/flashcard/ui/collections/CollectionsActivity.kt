package com.ferraz.flashcard.ui.collections

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@ExperimentalAnimationApi
@ExperimentalFoundationApi
class CollectionsActivity : ComponentActivity() {

    private val collections = elements
    private val detailScreen = CollectionDetailView()
    private val collectionsView = CollectionsView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "home") {
                navigationHome(navController)
            }
        }
    }

    private fun NavGraphBuilder.navigationHome(navController: NavHostController) = navigation(route = "home", startDestination = "collection-list") {

        composable(route = "collection-list") {
            collectionsView.Screen(collections) { collection ->
                navController.navigate("collection-detail/${collection?.uuid ?: 0}")
            }
        }

        composable(route = "collection-detail/{collection}", arguments = listOf(navArgument("collection") { type = NavType.IntType })) {
            it.arguments?.getLong("collection")?.let { collection ->
                detailScreen.Screen(collections.firstOrNull { it.uuid == collection })
            }
        }
    }
}


package com.ferraz.flashcard.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ferraz.flashcard.ui.AppNavigation.CARD_LIST_ROUT
import com.ferraz.flashcard.ui.cards.CardDetailView
import com.ferraz.flashcard.ui.cards.CardListView
import com.ferraz.flashcard.ui.collections.CollectionDetailView
import com.ferraz.flashcard.ui.collections.CollectionsView
import com.ferraz.flashcard.ui.collections.elements
import org.koin.android.ext.android.inject

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@ExperimentalAnimationApi
@ExperimentalFoundationApi
class CollectionsActivity : ComponentActivity() {

    private val collections = elements
    private val detailScreen = CollectionDetailView()
    private val collectionsView = CollectionsView()

    private val cardListView: CardListView by inject()
    private val cardDetailView: CardDetailView by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()
            Setup(navController = navController)

        }
    }

    @Composable
    fun Setup(navController: NavHostController) {

        NavHost(navController = navController, startDestination = CARD_LIST_ROUT) {

            /*
                composable(route = "collection-list") {
                    collectionsView.Screen(collections) { collection ->
                        navController.navigate("collection-detail/${collection?.uuid ?: 0}")
                    }
                }

                composable(route = "collection-detail/{collection}", arguments = listOf(navArgument("collection") { type = NavType.LongType })) {
                    it.arguments?.getLong("collection")?.let { collection ->
                        detailScreen.Screen(collections.firstOrNull { it.uuid == collection })
                    }
                }

             */

            composable(route = CARD_LIST_ROUT) {
                cardListView.Managed(navController = navController)
            }

            composable(route = "card-detail/{uuid}", arguments = listOf(navArgument("uuid") { type = NavType.LongType })) {
                cardDetailView.Managed(navController = navController, uuid = it.arguments?.getLong("uuid"))
            }

            composable(route = "card-detail") {
                cardDetailView.Managed(navController = navController, uuid = null)
            }
        }
    }

}

object AppNavigation {

    const val CARD_LIST_ROUT = "card-list"

    fun NavHostController.navigateToCardList() = navigate(CARD_LIST_ROUT)
    fun NavHostController.navigateToCardDetail(uuid: Long?) = navigate(if (uuid != null) "card-detail/$uuid" else "card-detail")
}


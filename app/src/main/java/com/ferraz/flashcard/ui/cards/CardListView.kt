package com.ferraz.flashcard.ui.cards

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ferraz.flashcard.domain.entities.CardEntity
import com.ferraz.flashcard.ui.AppNavigation.navigateToCardDetail
import com.ferraz.flashcard.ui.GenericView.fab
import com.ferraz.flashcard.ui.GenericView.topBar
import com.ferraz.flashcard.ui.cards.CardListView.Companion.Screen
import com.ferraz.flashcard.ui.cards.CardListViewModel.Failure
import com.ferraz.flashcard.ui.cards.CardListViewModel.Success
import com.ferraz.flashcard.ui.theme.FlashcardTheme
import com.ferraz.flashcard.ui.theme.StatusBar

@ExperimentalFoundationApi
class CardListView(private val viewModel: CardListViewModel) {

    @Composable
    fun Managed(navController: NavHostController) {
        when (val state = viewModel.cards.observeAsState().value) {
            is Success -> Screen(state.models, navController)
            is Failure -> Toast.makeText(LocalContext.current, state.e.message, Toast.LENGTH_SHORT).show()
        }
    }

    companion object {

        @Composable
        fun Screen(entities: List<CardEntity>, navController: NavHostController) = FlashcardTheme {

            StatusBar()

            Scaffold(
                topBar = topBar("Flash Cards"),
                floatingActionButton = fab(Icons.Filled.Add) {
                    navController.navigateToCardDetail(it?.uuid)
                }
            ) {
                BodyContent(entities) {
                    navController.navigateToCardDetail(it.uuid)
                }
            }
        }

        @Composable
        private fun BodyContent(entities: List<CardEntity>, onItemSelected: (CardEntity) -> Unit) {

            LazyVerticalGrid(
                cells = GridCells.Fixed(1),
                contentPadding = PaddingValues(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 85.dp)
            ) {

                items(items = entities) { entity ->
                    CardItem(onItemSelected, entity)
                }
            }
        }

        @Composable
        private fun CardItem(onItemSelected: (CardEntity) -> Unit, entity: CardEntity) {

            Card(
                modifier = Modifier
                    .padding(4.dp)
                    .clickable { onItemSelected(entity) }) {

                ConstraintLayout(Modifier.padding(8.dp)) {

                    val (front, divider, back) = createRefs()

                    Text(text = "Front: ${entity.front}",
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier
                            .fillMaxWidth()
                            .constrainAs(front) {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            })

                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp, bottom = 8.dp)
                            .constrainAs(divider) {
                                top.linkTo(front.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            })

                    Text(text = "Back: ${entity.back}",
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier
                            .fillMaxWidth()
                            .constrainAs(back) {
                                top.linkTo(divider.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                bottom.linkTo(parent.bottom)
                            })

                }
            }
        }
    }
}


/**
 * PREVIEW
 */

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Preview(name = "Cards Scaffold - List - Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Cards Scaffold - List - Light", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun CardListPreview(@PreviewParameter(FakeCardList::class) list: List<CardEntity>) {
    Screen(list, rememberNavController())
}

class FakeCardList(
    override val values: Sequence<List<CardEntity>> = sequenceOf(
        listOf(
            CardEntity(
                uuid = 1,
                front = "Card com texto completo para testar o tamanho da linha",
                back = "Card com texto completo para testar o tamanho da linha"
            ),
            CardEntity(uuid = 2, front = "Front", back = "Back"),
            CardEntity(uuid = 3, front = "Front", back = "Back"),
            CardEntity(uuid = 4, front = "Front", back = "Back"),
        )
    )
) : PreviewParameterProvider<List<CardEntity>>
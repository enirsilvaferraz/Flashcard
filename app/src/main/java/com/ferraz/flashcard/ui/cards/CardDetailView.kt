package com.ferraz.flashcard.ui.cards

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ferraz.flashcard.domain.entities.CardEntity
import com.ferraz.flashcard.ui.GenericView.topBar
import com.ferraz.flashcard.ui.cards.CardDetailView.Companion.Screen
import com.ferraz.flashcard.ui.theme.FlashcardTheme
import com.ferraz.flashcard.ui.theme.StatusBar

class CardDetailView(private val viewModel: CardDetailViewModel) {

    @Composable
    fun Managed(navController: NavHostController, uuid: Long?) {
        viewModel.getByID(uuid)
        Screen(navController = navController, entity = viewModel.entity.observeAsState().value ?: CardEntity()) {
            viewModel.save(it)
        }
    }

    companion object {

        @Composable
        fun Screen(navController: NavHostController, entity: CardEntity, onClick: (CardEntity) -> Unit) = FlashcardTheme {

            StatusBar()

            Scaffold(
                topBar = topBar("Flash Cards")
            ) {
                BodyContent(entity, onClick)
            }
        }

        @Composable
        private fun BodyContent(entity: CardEntity, onClick: (CardEntity) -> Unit) {

            val frontState = remember { mutableStateOf(TextFieldValue(entity.front)) }
            val backState = remember { mutableStateOf(TextFieldValue(entity.back)) }

            ConstraintLayout(modifier = Modifier.padding(32.dp)) {

                val (front, back, save) = createRefs()

                TextField(
                    value = frontState.value,
                    label = { Text("Front") },
                    onValueChange = { frontState.value = it },
                    modifier = Modifier
                        .height(200.dp)
                        .constrainAs(front) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                )

                TextField(
                    value = backState.value,
                    label = { Text("Back") },
                    onValueChange = { backState.value = it },
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .height(200.dp)
                        .constrainAs(back) {
                            top.linkTo(front.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                )

                Button(
                    onClick = {
                        onClick(CardEntity(front = frontState.value.text, back = backState.value.text))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .constrainAs(save) {
                            top.linkTo(back.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }) {
                    Text(text = "Save")
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
private fun CardListPreview(@PreviewParameter(FakeCardDetail::class) entity: CardEntity) {
    Screen(rememberNavController(), entity) {}
}

class FakeCardDetail(
    override val values: Sequence<CardEntity> = sequenceOf(
        CardEntity(
            uuid = 1,
            front = "Card com texto completo para testar o tamanho da linha",
            back = "Card com texto completo para testar o tamanho da linha"
        )
    )
) : PreviewParameterProvider<CardEntity>
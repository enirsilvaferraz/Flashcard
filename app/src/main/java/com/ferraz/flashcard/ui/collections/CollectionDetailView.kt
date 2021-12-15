package com.ferraz.flashcard.ui.collections

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.ferraz.flashcard.domain.entities.CollectionEntity
import com.ferraz.flashcard.ui.theme.FlashcardTheme

@ExperimentalComposeUiApi
class CollectionDetailView {

    private lateinit var f1: FocusRequester
    private lateinit var f2: FocusRequester

    @Composable
    fun Screen(collection: CollectionEntity?) {

        val (_f1, _f2) = FocusRequester.createRefs()
        f1 = _f1
        f2 = _f2

        var text by remember { mutableStateOf(collection?.name ?: "") }

        FlashcardTheme {

            val topBar = @Composable {
                TopAppBar(elevation = 0.dp, modifier = Modifier.height(100.dp)) {

                    ConstraintLayout(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 32.dp, end = 32.dp)
                    ) {
                        val (title) = createRefs()

                        TextField(
                            value = text,
                            onValueChange = { text = it },
                            modifier = Modifier.constrainAs(title) {
                                centerHorizontallyTo(parent)
                                bottom.linkTo(parent.bottom)
                            },
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = MaterialTheme.colors.background
                            ),
                            textStyle = MaterialTheme.typography.h6,
                            placeholder = {
                                Text(text = "Collection Name", style = MaterialTheme.typography.h6, modifier = Modifier.fillMaxWidth())
                            },
                            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words, imeAction = ImeAction.Next),
                            keyboardActions = KeyboardActions(
                                onNext = {
                                    // DO SOMETHING
                                    f1.requestFocus()
                                }
                            )
                        )
                    }
                }
            }

            val fab = @Composable {
                FloatingActionButton(
                    shape = MaterialTheme.shapes.large,
                    onClick = {
                        //    onFabClicked()
                    }
                ) { Icon(Icons.Filled.PlayArrow, "") }
            }

            val bottomBar = @Composable {
                BottomAppBar(cutoutShape = RoundedCornerShape(24.dp)) {}
            }

            Scaffold(
                topBar = topBar,
                floatingActionButton = fab,
                floatingActionButtonPosition = FabPosition.End,
                isFloatingActionButtonDocked = true,
                bottomBar = bottomBar
            ) {
                Content()
                AddCard()
            }
        }
    }

    @Composable
    private fun Content(){

    }

    @Composable
    private fun AddCard() {

        ConstraintLayout(Modifier.fillMaxSize()) {
            val (text) = createRefs()

            Card(modifier = Modifier.constrainAs(text) {
                top.linkTo(parent.top, margin = 32.dp)
                centerHorizontallyTo(parent)
            }) {

                ConstraintLayout {
                    val (front, back) = createRefs()

                    TextField(value = "Front",
                        onValueChange = {},
                        modifier = Modifier
                            .focusRequester(f1)
                            .constrainAs(front) {
                                top.linkTo(parent.top)
                                centerHorizontallyTo(parent)
                            },
                        keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences, imeAction = ImeAction.Next),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                // DO SOMETHING
                                f2.requestFocus()
                            }
                        ))

                    TextField(value = "Back",
                        onValueChange = {},
                        modifier = Modifier
                            .focusRequester(f2)
                            .constrainAs(back) {
                                top.linkTo(front.bottom)
                                centerHorizontallyTo(parent)
                            },
                        keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences, imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                // DO SOMETHING
                            }
                        ))

                }
            }
        }
    }

}

/**
 * PREVIEW
 */

@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Preview(name = "Collection Detail Scaffold - Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Collections Detail - Light", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun CollectionsActivityDarkMode(
    @PreviewParameter(FakeCollection::class) collection: CollectionEntity
) {
    CollectionDetailView().Screen(collection)
}

class FakeCollection(override val values: Sequence<CollectionEntity> = sequenceOf(elements[0])) : PreviewParameterProvider<CollectionEntity>
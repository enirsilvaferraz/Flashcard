package com.ferraz.flashcard.ui.collections

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.ferraz.flashcard.domain.entities.CollectionEntity
import com.ferraz.flashcard.ui.theme.FlashcardTheme
import com.ferraz.flashcard.ui.theme.StatusBar

@ExperimentalMaterialApi
@ExperimentalFoundationApi
class CollectionsView {

    @Composable
    fun Screen(collections: List<CollectionEntity>, performCardSelection: (CollectionEntity?) -> Unit) {

        FlashcardTheme {

            StatusBar()

            Scaffold(
                topBar = { TopBar() },
                floatingActionButton = { Fab(performCardSelection) },
                floatingActionButtonPosition = FabPosition.End,
            ) {
                BodyContent(collections = collections, onCardClick = performCardSelection)
            }
        }
    }

    @Composable
    private fun Fab(performCardSelection: (CollectionEntity?) -> Unit) {
        FloatingActionButton(
            shape = MaterialTheme.shapes.large,
            onClick = { performCardSelection(null) }
        ) {
            Icon(Icons.Filled.Add, "")
        }
    }

    @Composable
    private fun TopBar() {

        TopAppBar(elevation = 0.dp) {
            ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                Text(text = "Collections",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.constrainAs(createRef()) {
                        centerTo(parent)
                    })
            }
        }
    }

    @Composable
    private fun BodyContent(collections: List<CollectionEntity>, onCardClick: (CollectionEntity) -> Unit) {

        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 85.dp)
        ) {

            items(items = collections) { collection ->

                Card(
                    elevation = 6.dp,
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier
                        .requiredHeight(100.dp)
                        .padding(8.dp)
                        .clickable {
                            onCardClick(collection)
                        }
                ) {

                    ConstraintLayout(modifier = Modifier.padding(16.dp)) {

                        val (title) = createRefs()

                        Text(
                            text = collection.name,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.constrainAs(title) {
                                centerTo(parent)
                            }
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun PanelAddCollection() {

    }

    @Composable
    fun Dialog(dialogOpened: MutableState<Boolean>) {

        //val dialogOpened = remember { mutableStateOf(false) }

        if (dialogOpened.value) {
            var text by remember { mutableStateOf("") }

            AlertDialog(
                backgroundColor = MaterialTheme.colors.onSurface,
                onDismissRequest = { dialogOpened.value = false },
                //title = { Text("New Collection") },
                text = {
                    Column {
                        TextField(value = text, onValueChange = { text = it })
                    }
                },
                confirmButton = {
                    Button(onClick = {
                        dialogOpened.value = false
                    }) {
                        Text(text = "Done")
                    }
                })
        }
    }
}

/**
 * PREVIEW
 */

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Preview(name = "Collections Scaffold - Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Collections Scaffold - Light", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun CollectionsActivityDarkMode(
    @PreviewParameter(FakeCollections::class) collections: List<CollectionEntity>
) {
    CollectionsView().Screen(collections = collections) {}
}

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Preview(name = "Collections Scaffold - Dark 1", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Collections Scaffold - Light 1", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun CollectionsActivityDarkModeEmpty(
    @PreviewParameter(FakeCollectionsEmpty::class) collections: List<CollectionEntity>
) {
    CollectionsView().Screen(collections = collections) {}
}

val elements = listOf(
    CollectionEntity(1, "For Beginners", listOf()),
    CollectionEntity(2, "Verbs", listOf()),
    CollectionEntity(3, "Phrasal Verbs", listOf()),
    CollectionEntity(4, "Grammar Advanced - Book 1", listOf()),
    CollectionEntity(5, "Grammar Advanced - Book 2", listOf()),
    CollectionEntity(6, "Grammar Advanced - Book 3", listOf()),
    CollectionEntity(7, "Advanced 1", listOf()),
    CollectionEntity(8, "For Beginners", listOf()),
    CollectionEntity(9, "Verbs", listOf()),
    /*CollectionEntity(10, "Phrasal Verbs", listOf()),
    CollectionEntity(11, "Grammar Advanced - Book 1", listOf()),
    CollectionEntity(12, "Grammar Advanced - Book 2", listOf()),
    CollectionEntity(13, "Grammar Advanced - Book 3", listOf()),
    CollectionEntity(14, "Advanced 1", listOf()),
    CollectionEntity(15, "For Beginners", listOf()),
    CollectionEntity(16, "Verbs", listOf()),
    CollectionEntity(17, "Phrasal Verbs", listOf()),
    CollectionEntity(18, "Grammar Advanced - Book 1", listOf()),
    CollectionEntity(19, "Grammar Advanced - Book 2", listOf()),
    CollectionEntity(20, "Grammar Advanced - Book 3", listOf()),
    CollectionEntity(21, "Advanced 1", listOf())

     */
)

class FakeCollections(override val values: Sequence<List<CollectionEntity>> = sequenceOf(elements)) : PreviewParameterProvider<List<CollectionEntity>>
class FakeCollectionsEmpty(override val values: Sequence<List<CollectionEntity>> = sequenceOf(emptyList())) : PreviewParameterProvider<List<CollectionEntity>>
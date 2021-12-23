package com.ferraz.flashcard.ui.cards

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.ferraz.flashcard.domain.entities.CardEntity
import com.ferraz.flashcard.ui.theme.FlashcardTheme
import com.ferraz.flashcard.ui.theme.StatusBar

class CardListView {

    @Composable
    fun Screen() {
        FlashcardTheme {

            StatusBar()
            
            Scaffold {
                BodyContent()
            }
        }
    }

    @Composable
    private fun BodyContent() {
    
        Card {
            Text(text = "Text")
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
    CardListView().Screen()
}

class FakeCardList(override val values: Sequence<List<CardEntity>> = sequenceOf(emptyList())) : PreviewParameterProvider<List<CardEntity>>
package com.ferraz.flashcard.ui.collections

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.ferraz.flashcard.domain.entities.CollectionEntity

class CollectionAddView {

    @Composable
    fun Screen(){


    }
}

/**
 * PREVIEW
 */

@ExperimentalFoundationApi
@Preview(name = "Collections Scaffold - Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CollectionsActivityDarkMode(
    @PreviewParameter(FakeCollections::class) collections: List<CollectionEntity>
) {
    CollectionAddView().Screen()
}

@ExperimentalFoundationApi
@Preview(name = "Collections Scaffold - Light", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun CollectionsActivityLightMode(
    @PreviewParameter(FakeCollections::class) collections: List<CollectionEntity>
) {
    CollectionAddView().Screen()
}
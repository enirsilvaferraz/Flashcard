package com.ferraz.flashcard.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.ferraz.flashcard.domain.entities.GenericEntity

object GenericView {

    @Composable
    fun topBar(title: String): @Composable () -> Unit = {

        TopAppBar(elevation = 0.dp) {
            ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                Text(text = title,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.constrainAs(createRef()) {
                        centerTo(parent)
                    })
            }
        }
    }

    @Composable
    fun fab(icon: ImageVector, onClick: (GenericEntity?) -> Unit): @Composable () -> Unit = {
        FloatingActionButton(
            shape = MaterialTheme.shapes.large,
            onClick = { onClick(null) }
        ) {
            Icon(icon, "")
        }
    }
}
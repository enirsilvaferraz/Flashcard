package com.ferraz.flashcard.ui

import android.app.Application
import androidx.compose.foundation.ExperimentalFoundationApi
import com.ferraz.flashcard.domain.DomainKoinModule.datasources
import com.ferraz.flashcard.domain.DomainKoinModule.repositories
import com.ferraz.flashcard.domain.DomainKoinModule.useCases
import com.ferraz.flashcard.ui.UiKoinModules.viewmodels
import com.ferraz.flashcard.ui.UiKoinModules.views
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@ExperimentalFoundationApi
open class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AppApplication)
            modules(datasources, repositories, useCases, viewmodels, views)
        }
    }
}
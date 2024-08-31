package com.avm.triviaapp.core

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * App.kt
 *
 * This is the main entry point of the application. It initializes the global application context
 * and sets up dependency injection with Hilt.
 *
 */
@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        // Initialization code can go here if needed in the future
    }
}
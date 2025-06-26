package org.sleepapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.sleepapp.di.androidDatabaseModule
import org.sleepapp.di.appModule
import org.sleepapp.di.initKoin


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initKoin {
            androidContext(this@MainActivity)
            modules(androidDatabaseModule, appModule)
            androidLogger()
        }

        setContent{

            App()
        }
    }
}

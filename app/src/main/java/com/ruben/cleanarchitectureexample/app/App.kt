package com.ruben.cleanarchitectureexample.app

import android.app.Application
import com.ruben.data.di.apiModule
import com.ruben.data.di.databaseModule
import com.ruben.data.di.repositoryModule
import com.ruben.domain.di.interactorModule
import com.ruben.cleanarchitectureexample.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(modules)
        }
    }

    val modules = listOf(
        apiModule,
        databaseModule,
        interactorModule,
        repositoryModule,
        viewModelModule
    )
}
package com.ruben.data.di

import android.app.Application
import androidx.room.Room
import com.ruben.data.dataservice.apiservice.AllApiServices
import com.ruben.data.dataservice.sqlservice.AppDB
import com.ruben.data.datastore.CoursesListRepository
import com.ruben.data.repository.CoursesListRepositoryImpl
import com.ruben.data.utils.HeaderInterceptor
import com.ruben.entities.Constants.Companion.BASE_URL
import com.squareup.moshi.Moshi

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory


import retrofit2.converter.moshi.MoshiConverterFactory

val apiModule = module {

    single { Moshi.Builder().build() }
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .apply {
                client(
                    OkHttpClient.Builder()
                        .addInterceptor(HeaderInterceptor())
                        .addInterceptor(HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        })
                        .build()
                )
            }
            .build()
    }
    single<AllApiServices> { get<Retrofit>().create(AllApiServices::class.java) }

}
val databaseModule = module {
    fun provideDatabase(application: Application): AppDB {
        return Room.databaseBuilder(application, AppDB::class.java, "MyAppDB")
            .allowMainThreadQueries()
            .build()
    }
    single { provideDatabase(androidApplication()).coursesDao }

}
val repositoryModule = module {
    single<CoursesListRepository> { CoursesListRepositoryImpl(get(),get()) }
}
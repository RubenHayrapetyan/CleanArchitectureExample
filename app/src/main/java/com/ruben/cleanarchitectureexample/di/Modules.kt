package com.ruben.cleanarchitectureexample.di

import com.ruben.cleanarchitectureexample.fragment.courses.CoursesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CoursesViewModel(get()) }
}

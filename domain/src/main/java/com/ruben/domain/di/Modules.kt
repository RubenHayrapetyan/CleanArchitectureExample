package com.ruben.domain.di

import com.ruben.domain.interactors.CoursesListInteractor
import com.ruben.domain.usecase.CoursesUseCase
import org.koin.dsl.module

val interactorModule = module {
    factory <CoursesListInteractor> { CoursesUseCase(get()) }
}

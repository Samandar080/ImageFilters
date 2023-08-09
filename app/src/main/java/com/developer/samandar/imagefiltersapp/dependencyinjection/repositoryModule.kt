package com.developer.samandar.imagefiltersapp.dependencyinjection

import com.developer.samandar.imagefiltersapp.repositories.EditImageRepository
import com.developer.samandar.imagefiltersapp.repositories.EditImageRepositoryImpl
import com.developer.samandar.imagefiltersapp.repositories.SavedImagesRepository
import com.developer.samandar.imagefiltersapp.repositories.SavedImagesRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    factory<EditImageRepository> { EditImageRepositoryImpl(androidContext()) }
    factory<SavedImagesRepository> {SavedImagesRepositoryImpl(androidContext())}
}
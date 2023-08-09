package com.developer.samandar.imagefiltersapp.dependencyinjection

import com.developer.samandar.imagefiltersapp.viewmodels.EditImageViewModel
import com.developer.samandar.imagefiltersapp.viewmodels.SavedImageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { EditImageViewModel(editImageRepository = get()) }
    viewModel{ SavedImageViewModel(savedImagesRepository = get ()) }
}
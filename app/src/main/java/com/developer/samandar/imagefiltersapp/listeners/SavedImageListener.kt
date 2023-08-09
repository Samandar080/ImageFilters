package com.developer.samandar.imagefiltersapp.listeners

import java.io.File

interface SavedImageListener {
    fun onImageClicked(file: File)
}
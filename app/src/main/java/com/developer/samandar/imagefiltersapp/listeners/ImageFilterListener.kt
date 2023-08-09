package com.developer.samandar.imagefiltersapp.listeners

import com.developer.samandar.imagefiltersapp.data.ImageFilter

interface ImageFilterListener {
    fun onFilterSelected(imageFilter: ImageFilter)
}
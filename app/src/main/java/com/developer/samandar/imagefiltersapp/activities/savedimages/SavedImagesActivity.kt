package com.developer.samandar.imagefiltersapp.activities.savedimages

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.FileProvider
import com.developer.samandar.imagefiltersapp.R
import com.developer.samandar.imagefiltersapp.activities.editimage.EditImageActivity
import com.developer.samandar.imagefiltersapp.activities.filteredimage.FilteredImageActivity
import com.developer.samandar.imagefiltersapp.adapter.SavedImagesAdapter
import com.developer.samandar.imagefiltersapp.databinding.ActivitySavedImagesBinding
import com.developer.samandar.imagefiltersapp.listeners.SavedImageListener
import com.developer.samandar.imagefiltersapp.utilities.displayToast
import com.developer.samandar.imagefiltersapp.viewmodels.EditImageViewModel
import com.developer.samandar.imagefiltersapp.viewmodels.SavedImageViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File

class SavedImagesActivity : AppCompatActivity(), SavedImageListener {

    private lateinit var binding: ActivitySavedImagesBinding
    private val viewModel: SavedImageViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySavedImagesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupObservers()
        setListeners()
        viewModel.loadSavedImages()
    }
    private fun setupObservers(){
        viewModel.savedImageUiState.observe(this,{
            val savedImagesDataState = it?: return@observe
            binding.savedImagesProgressBar.visibility =
                if (savedImagesDataState.isLoading) View.VISIBLE else View.GONE
            savedImagesDataState.savedImages?.let { savedImages->
                SavedImagesAdapter(savedImages, this).also { adapter->
                    with(binding.savedImagesRecyclerView){
                        this.adapter = adapter
                        visibility = View.VISIBLE
                    }
                }
            }?: run {
                savedImagesDataState.error?.let { error->
                    displayToast(error)
                }
            }
        })
    }
    private fun setListeners(){
        binding.imageBack.setOnClickListener { onBackPressed() }
    }

    override fun onImageClicked(file: File) {
        val fileUri = FileProvider.getUriForFile(
            applicationContext,
            "${packageName}.provider",
            file
        )
        Intent(
            applicationContext,
            FilteredImageActivity::class.java
        ).also { filteredImageIntent->
            filteredImageIntent.putExtra(EditImageActivity.KEY_FILTERED_IMAGE_URI, fileUri)
            startActivity(filteredImageIntent)
        }
    }
}
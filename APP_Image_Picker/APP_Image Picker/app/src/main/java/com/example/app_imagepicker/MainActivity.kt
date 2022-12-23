package com.example.app_imagepicker

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import coil.load
import com.example.app_imagepicker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var imagePath1: Uri? = null
    private var imagePath2: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getImage1 =
            registerForActivityResult(ActivityResultContracts.GetContent()) {
                uri ->
                // save the image's path
                imagePath1 = uri
                binding.imagePreview1.apply {
                    scaleType = ImageView.ScaleType.CENTER_CROP
                    load(uri) {
                        size(60, 60) // in pixels
                    }
                }
            }

        val getImage2 =
            registerForActivityResult(ActivityResultContracts.GetContent()) {
                    uri ->
                // save the image's path
                imagePath2 = uri
                binding.imagePreview2.apply {
                    scaleType = ImageView.ScaleType.CENTER_CROP
                    load(uri) {
                        size(60, 60) // in pixels
                    }
                }
            }

        binding.button1Add.setOnClickListener{
            getImage1.launch("image/*")
        }

        binding.button2Add.setOnClickListener{
            getImage2.launch("image/*")
        }

        binding.button1Delete.setOnClickListener {
            imagePath1 = null
            binding.imagePreview1.setImageResource(android.R.drawable.ic_menu_camera)
            // not R.drawable
        }

        binding.button2Delete.setOnClickListener {
            imagePath2 = null
            binding.imagePreview2.setImageResource(android.R.drawable.ic_menu_camera)
            // not R.drawable
        }

        binding.imagePreview1.setOnClickListener{
            binding.imageView.load(imagePath1)
        }

        binding.imagePreview2.setOnClickListener{
            binding.imageView.load(imagePath2)
        }
    }
}
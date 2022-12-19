package com.example.roomdatabaseex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdatabaseex.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: InventoryViewModel by viewModels {
        InventoryViewModelFactory(
            (application as InventoryApplication).database.itemDao()
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //recyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = ItemListAdapter {}
        binding.recyclerView.adapter = adapter
        // observe the livedata
        viewModel.allItems.observe(this) {
            adapter.submitList(it)
        }

        // add Button
        binding.addAction.setOnClickListener {
            addNewUser()

            val inputMethodManager = this.getSystemService(INPUT_METHOD_SERVICE) as
                    InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(this.currentFocus?.windowToken, 0)
        }
    }

    private fun addNewUser(){
        if (isEntryValid()) {
            viewModel.addNewUser(
                binding.userName.text.toString(),
                binding.userAge.text.toString(),
            )
        }
    }

    private fun isEntryValid(): Boolean{
        return viewModel.isEntryValid(
            binding.userName.text.toString(),
            binding.userAge.text.toString(),
        )
    }


}
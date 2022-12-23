package com.example.inventory

import android.content.ClipData
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.inventorystarter.InventoryApplication
import com.example.inventorystarter.InventoryViewModel
import com.example.inventorystarter.InventoryViewModelFactory
import com.example.inventorystarter.data.Item
import com.example.inventorystarter.databinding.FragmentItemDetailBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class ItemDetailFragment : Fragment() {

    private lateinit var binding: FragmentItemDetailBinding
    private val viewModel: InventoryViewModel by activityViewModels {
        InventoryViewModelFactory(
            (activity?.application as InventoryApplication).database.itemDao()
        )
    }
    private lateinit var item: Item

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentItemDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // get the passing data
        val args: ItemDetailFragmentArgs by navArgs()
        val id = args.itemId
        // retrieve the item with the id and observe it
        viewModel.retrieveItem(id).observe(viewLifecycleOwner) {
            selectedItem ->
            item = selectedItem
            bind(item)
        }

        binding.deleteItem.setOnClickListener { showConfirmationDialog() }
    }

    private fun showConfirmationDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Delete the item")
            .setMessage("Are you sure?")
            .setCancelable(false)
            .setNegativeButton("No") {_, _ -> }
            .setPositiveButton("Yes") {_, _ ->
                // perform deletion
                deleteItem()
            }
            .show()
    }

    private fun deleteItem() {
        viewModel.deleteItem(item)
        findNavController().navigateUp()
    }

    // display item data on the screen
    private fun bind(item: Item) {
        binding.apply {
            itemName.text = item.itemName
            itemPrice.text = item.itemPrice.toString()
            itemCount.text = item.quantityInStock.toString()
        }
    }



}
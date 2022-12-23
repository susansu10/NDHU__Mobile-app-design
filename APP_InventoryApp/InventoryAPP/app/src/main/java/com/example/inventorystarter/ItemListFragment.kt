package com.example.inventorystarter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.inventorystarter.adapter.ItemListAdapter
import com.example.inventorystarter.databinding.FragmentItemListBinding

class ItemListFragment : Fragment() {

    private lateinit var binding: FragmentItemListBinding
    private val viewModel: InventoryViewModel by activityViewModels {
        InventoryViewModelFactory(
            (activity?.application as InventoryApplication).database.itemDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentItemListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = ItemListAdapter {
            // handle item selection
            val action =
                ItemListFragmentDirections.actionItemListFragmentToItemDetailFragment(it.id)
            this.findNavController().navigate(action)
        }
        binding.recyclerView.adapter = adapter
        // observe the livedata
        viewModel.allItems.observe(viewLifecycleOwner) {
            items ->
            items.let {
                adapter.submitList(it)
            }
        }


        binding.addButton.setOnClickListener {
            findNavController().navigate(R.id.action_itemListFragment_to_addItemFragment)
        }
    }


}
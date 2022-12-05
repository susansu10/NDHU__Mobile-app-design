package com.example.listofsports

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listofsports.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel: MyViewModel by viewModels()
        val layoutManager = LinearLayoutManager(this.context)
        binding.recyclerView.layoutManager = layoutManager
        val adapter = BallAdapter() {
        }
        // coding here
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(this.context,DividerItemDecoration.VERTICAL)
        )
        binding.recyclerView.setHasFixedSize(true)

        // observe any changes on the data source
        viewModel.ballList.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it)
            }
        }
        // enable options menu
        setHasOptionsMenu(true)
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }
//
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        requireView().findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAboutFragment2("Angel",25))
        return item.onNavDestinationSelected(requireView().findNavController()) || super.onOptionsItemSelected(item)
    }
}
package com.example.app_cityweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.example.app_cityweather.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // both data and view binding can share the same binding codes
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // get the viewModel
        viewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)

        viewModel.sendRetrofitRequest("Tokyo")

        // config the binding variable
        binding.viewModel = viewModel
        binding.lifecycleOwner = this // activity

        // config the spinner
        // set the data source
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, viewModel.cities)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = adapter

        // handle the item selection
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val selectedPosition = p0?.selectedItemPosition // p0 means parent here
                selectedPosition?.let {
                    viewModel.sendRetrofitRequest(viewModel.cities[it])
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
    }

}
package com.example.listofsports

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    //data source as LiveData
    val ballList = MutableLiveData<List<Ball>>().apply {
        val ballListSource = ArrayList<Ball>().apply { //temporary array
            repeat(1) {
                add(Ball("Baseball", R.drawable.baseball))
                add(Ball("Basketball", R.drawable.basketball))
                add(Ball("Football", R.drawable.football))
                add(Ball("Volleyball", R.drawable.volleyball))
            }
        }
        value = ballListSource
    }

}
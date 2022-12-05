package com.example.diceroller

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RollerViewModel(): ViewModel() {
    private val dice = Dice(6)
    var diceroll = MutableLiveData<Int>(1)
    fun rollDice(){
        diceroll.value = dice.roll()
    }
    class Dice(private val numSides: Int) {

        /**
         * Do a random dice roll and return the result.
         */
        fun roll(): Int {
            return (1..numSides).random()
        }
    }
}

@BindingAdapter("setImage")
fun ImageView.setDiceImage(rollNumber: Int?){
    rollNumber?.let {
        val drawableResource = when (rollNumber) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        setImageResource(drawableResource)
    }
}
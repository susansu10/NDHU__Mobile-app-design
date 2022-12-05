package com.example.app_01temperaturetransformation

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val input_F: EditText = findViewById(R.id.editTextNumber2)
        val input_C: EditText = findViewById(R.id.editTextNumber)

        val covertButton01: Button = findViewById(R.id.button)
        covertButton01.setOnClickListener {
            Toast.makeText(this, "Start to covert your temperature !", Toast.LENGTH_SHORT).show()
            val result_FtoC = covert_F(input_F.text.toString().toFloat())

            val resultConvert: TextView = findViewById(R.id.editTextTextPersonName2)
            resultConvert.text = result_FtoC.toString()
        }
        val covertButton02: Button = findViewById(R.id.button2)
        covertButton02.setOnClickListener {
            Toast.makeText(this, "Start to covert your temperature !", Toast.LENGTH_SHORT).show()
            val result_CtoF = covert_C(input_C.text.toString().toFloat())

            val resultConvert: TextView = findViewById(R.id.editTextTextPersonName)
            resultConvert.text = result_CtoF.toString()
        }
    }

    private fun covert_C(cal_C: Float): Float {
        // convert temperature
        val result_CtoF = (cal_C) * 9 / 5 + 32
        return(result_CtoF)
    }
    private fun covert_F(cal_F: Float): Float {
        // convert temperature
        val result_FtoC = (cal_F - 32)* 5/9
        return(result_FtoC)
    }
}




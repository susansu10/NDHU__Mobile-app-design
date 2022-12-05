package com.example.ball

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val input_x: EditText = findViewById(R.id.editTextNumber)
        val input_y: EditText = findViewById(R.id.editTextNumber2)

        val covertButton01: Button = findViewById(R.id.button) // +
        covertButton01.setOnClickListener {
            Toast.makeText(this, "Start to add your number", Toast.LENGTH_SHORT).show()
            val result_plus = covert_plus(input_x.text.toString().toFloat(), input_y.text.toString().toFloat())

            val resultConvert1: TextView = findViewById(R.id.textView2)
            resultConvert1.text = "${input_x.text} + ${input_y.text} = $result_plus"
        }

        val covertButton02: Button = findViewById(R.id.button2) // -
        covertButton02.setOnClickListener {
            Toast.makeText(this, "Start to minus your number", Toast.LENGTH_SHORT).show()
            val result_minus = covert_minus(input_x.text.toString().toFloat(), input_y.text.toString().toFloat())

            val resultConvert2: TextView = findViewById(R.id.textView2)
            resultConvert2.text = "${input_x.text} - ${input_y.text} = $result_minus"
        }

        val covertButton03: Button = findViewById(R.id.button3) // *
        covertButton03.setOnClickListener {
            Toast.makeText(this, "Start to mutiply your number", Toast.LENGTH_SHORT).show()
            val result_goo = covert_go(input_x.text.toString().toFloat(), input_y.text.toString().toFloat())

            val resultConvert3: TextView = findViewById(R.id.textView2)
            resultConvert3.text = "${input_x.text} * ${input_y.text} = $result_goo"
        }

        val covertButton04: Button = findViewById(R.id.button4) // 除
        covertButton04.setOnClickListener {
            Toast.makeText(this, "Start to 除 your number", Toast.LENGTH_SHORT).show()
            val result_divide = covert_divide(input_x.text.toString().toFloat(), input_y.text.toString().toFloat())

            val resultConvert4: TextView = findViewById(R.id.textView2)
            if(input_x.text.toString().toInt() == 0)
            {
                resultConvert4.text = "${input_x.text} / ${input_y.text} = 0"
            }
            else
            {
                resultConvert4.text = "${input_x.text} / ${input_y.text} = $result_divide"
            }
        }
    }

    private fun covert_plus(cal_p: Float, cal_p2: Float): Float { // +
        val result_p = (cal_p + cal_p2)
        return(result_p)
    }
    private fun covert_minus(cal_m: Float, cal_m2: Float): Float { // -
        println("-")
        val result_m = (cal_m - cal_m2)
        return(result_m)
    }
    private fun covert_go(cal_g: Float, cal_g2: Float): Float { // *
        println("*")
        val result_g = (cal_g * cal_g2)
        return(result_g)
    }
    private fun covert_divide(cal_d: Float, cal_d2: Float): Float { // 除
        val result_d = (cal_d / cal_d2)
        return(result_d)
    }
}
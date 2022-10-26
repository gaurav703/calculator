package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var tvInput: TextView? = null
    var lastNumeric: Boolean = false
    var lastDot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)

        /*  val Button0 :  Button = findViewById(R.id.Button0)
        val Button1 : Button = findViewById(R.id.Button1)
        val Button2 : Button = findViewById(R.id.Button2)
        val Button3 : Button = findViewById(R.id.Button3)
        val Button4 : Button = findViewById(R.id.Button4)
        val Button5 : Button = findViewById(R.id.Button5)
        val Button6 : Button = findViewById(R.id.Button6)
        val Button7 : Button = findViewById(R.id.Button7)
        val Button8 : Button = findViewById(R.id.Button8)
        val Button9 : Button = findViewById(R.id.Button9)*/


        /*   Button0.setOnClickListener(){
            tvInput?.text = "0"
        }
        Button7.setOnClickListener(){
            tvInput?.text = "7"
        }
        Button1.setOnClickListener(){
            tvInput?.text = "1"
        }
        Button2.setOnClickListener(){
            tvInput?.text = "2"
        }
        Button3.setOnClickListener(){
            tvInput?.text = "3"
        }
        Button4.setOnClickListener(){
            tvInput?.text = "4"
        }
        Button5.setOnClickListener(){
            tvInput?.text = "5"
        }
        Button6.setOnClickListener(){
            tvInput?.text = "6"
        }
        Button8.setOnClickListener(){
            tvInput?.text = "8"
        }
        Button9.setOnClickListener(){
            tvInput?.text = "9"
        }

*/

    }

    fun onDigit(view: View) {
        // tvInput?.append("1")
        tvInput?.append((view as Button).text)
        lastNumeric = true
        lastDot = false
    }
    fun onClear(view: View) {
        tvInput?.text = " "
    }
    fun onDecimalpoint(view: View) {
        if (lastNumeric  && !lastDot) {
            tvInput?.append(".")
            lastNumeric = false
            lastDot = true
        }
    }
    fun onOperator(view: View) {
        tvInput?.text?.let {
            if ( lastNumeric  && !onOperatoradd(it.toString())) {
                tvInput?.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }
        }
    }

    fun onEqual(view: View){
          if(lastNumeric){
              var tvvalue = tvInput?.text.toString()
              var prefix = "-"
              try {
                  if (tvvalue.startsWith("-")) {
                      prefix = "-"
                      tvvalue = tvvalue.substring(1)

                  }
                  if (tvvalue.contains("-")) {
                      val splitvalue =
                          tvvalue.split("-")// spilt the value and enter the value in array
                      var one = splitvalue[0]
                      var two = splitvalue[1]

                      if( prefix.isNotEmpty()){
                          one = prefix + one
                      }
                        var result = removeZero((one.toDouble() - two.toDouble()).toString())
                      tvInput?.text = result


                  }else if (tvvalue.contains("+")) {
                      val splitvalue =
                          tvvalue.split("+")// spilt the value and enter the value in array
                      var one = splitvalue[0]
                      var two = splitvalue[1]


                       var result = removeZero((one.toDouble() + two.toDouble()).toString())
                      tvInput?.text = result


                  }else if (tvvalue.contains("/")) {
                      val splitvalue =
                          tvvalue.split("/")// spilt the value and enter the value in array
                      var one = splitvalue[0]
                      var two = splitvalue[1]



                       var result = removeZero((one.toDouble() / two.toDouble()).toString())
                      tvInput?.text = result


                  }else if (tvvalue.contains("*")) {
                      val splitvalue =
                          tvvalue.split("*")// spilt the value and enter the value in array
                      var one = splitvalue[0]
                      var two = splitvalue[1]


                       var result = removeZero((one.toDouble() * two.toDouble()).toString())
                      tvInput?.text = result
                  }
              }
              catch (e : java.lang.ArithmeticException){
                  e.printStackTrace()
              }
          }


    }

    private fun removeZero( result: String ): String {
        var value = result
        if( result.contains(".0"))
           value = result.substring(0,result.length - 2 )
           return value

    }
    private fun onOperatoradd(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/")
                    || value.contains("-")
                    || value.contains("+")
                    || value.contains("*")

        }
    }
}
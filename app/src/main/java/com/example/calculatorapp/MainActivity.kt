package com.example.calculatorapp

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var display: TextView
    private lateinit var zero: Button
    private lateinit var one: Button
    private lateinit var two: Button
    private lateinit var three: Button
    private lateinit var four: Button
    private lateinit var five: Button
    private lateinit var six: Button
    private lateinit var seven: Button
    private lateinit var eight: Button
    private lateinit var nine: Button
    private lateinit var plusMinus: Button
    private lateinit var decimal: Button
    private lateinit var multiply: Button
    private lateinit var divide: Button
    private lateinit var add: Button
    private lateinit var subtract: Button
    private lateinit var clear: Button
    private lateinit var equals: Button
    private lateinit var del: Button

    private var result = 0f
    private var fNum = ""
    private var sNum = ""
    private var operator = ' '


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //_____________________________________________________
        //           findViewById
        //_____________________________________________________

        display = findViewById(R.id.textOutbut)

        zero = findViewById(R.id.b0)
        one = findViewById(R.id.b1)
        two = findViewById(R.id.b2)
        three = findViewById(R.id.b3)
        four = findViewById(R.id.b4)
        five = findViewById(R.id.b5)
        six = findViewById(R.id.b6)
        seven = findViewById(R.id.b7)
        eight = findViewById(R.id.b8)
        nine = findViewById(R.id.b9)

        add = findViewById(R.id.Add)
        subtract = findViewById(R.id.Sub)
        multiply = findViewById(R.id.Mult)
        divide = findViewById(R.id.Div)
        equals = findViewById(R.id.Equals)

        decimal = findViewById(R.id.Decimal)
        plusMinus = findViewById(R.id.Negative)
        clear = findViewById(R.id.Clear)
        del = findViewById(R.id.Del)

        //_____________________________________________________
        //           setOnClickListener
        //_____________________________________________________

        zero.setOnClickListener { setNum('0') }
        one.setOnClickListener { setNum('1') }
        two.setOnClickListener { setNum('2') }
        three.setOnClickListener { setNum('3') }
        four.setOnClickListener { setNum('4') }
        five.setOnClickListener { setNum('5') }
        six.setOnClickListener { setNum('6') }
        seven.setOnClickListener { setNum('7') }
        eight.setOnClickListener { setNum('8') }
        nine.setOnClickListener { setNum('9') }


        add.setOnClickListener { addOperator('+') }
        subtract.setOnClickListener { addOperator('-') }
        multiply.setOnClickListener { addOperator('*') }
        divide.setOnClickListener { addOperator('/') }
        equals.setOnClickListener { calculate() }

        decimal.setOnClickListener {
            if(operator==' '&&!fNum.contains(".")){setNum('.')}
            else {setNum('.')}
        }

        plusMinus.setOnClickListener { NegativeFun() }

        clear.setOnClickListener { clear() }
        del.setOnClickListener { delete() }
    }

    private fun NegativeFun (){
        if(operator==' '){
            fNum = if(fNum.startsWith("-")){
                fNum.substring(1, fNum.length)
            } else{
                "-$fNum"
            }
            display.text = fNum

        }else{
            sNum = if(sNum.startsWith("-")){
                sNum.substring(1, sNum.length)
            } else{
                "-$sNum"
            }
            val text = fNum + operator + sNum
            display.text = text
        }
    }

    private fun setNum(num: Char){
        if(operator==' '){
            fNum += num
            display.text = fNum
        }else{
            sNum += num
            val text = fNum + operator + sNum
            display.text = text
        }
    }

    private fun addOperator(op: Char){
        operator = op
        val text = fNum + operator
        display.text = text
    }

    private fun calculate(){
        when (operator) {
            '+' -> result = fNum.toFloat() + sNum.toFloat()
            '-' -> result = fNum.toFloat() - sNum.toFloat()
            '*' -> result = fNum.toFloat() * sNum.toFloat()
            '/' -> if (fNum.toFloat() != 0f && sNum.toFloat() != 0f) {
                       result = fNum.toFloat() / sNum.toFloat() }
        }
        fNum = result.toString()
        sNum = ""
        display.text = result.toString()
    }

    private fun clear(){
        result = 0f
        operator = ' '
        fNum = ""
        sNum = ""
        display.text = "0"
    }

    private fun delete(){
        if(operator==' '){
            if(fNum.isNotEmpty()){
                fNum = fNum.substring(0, fNum.length - 1)
                if(fNum.isEmpty()){display.text = "0"}
                else{display.text = fNum}
            }
        }else{
            if(sNum.isNotEmpty()){
                sNum = sNum.substring(0, sNum.length - 1)
                val text = fNum + operator + sNum
                display.text = text
            }else{
                operator=' '
                display.text = fNum
            }
        }
    }
}
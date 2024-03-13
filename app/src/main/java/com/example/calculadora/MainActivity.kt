package com.example.calculadora

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val sum: (Double, Double) -> Double = { a, b -> a + b }
        val subtraction: (Double, Double) -> Double = { a, b -> a - b }
        val multiplication: (Double, Double) -> Double = { a, b -> a * b }
        val division: (Double, Double) -> Double = { a, b -> a / b }

        findViewById<Button>(R.id.buttonSum).setOnClickListener{calculate(sum)}
        findViewById<Button>(R.id.buttonSub).setOnClickListener{calculate(subtraction)}
        findViewById<Button>(R.id.buttonMulti).setOnClickListener{calculate(multiplication)}
        findViewById<Button>(R.id.buttonDivide).setOnClickListener{calculate(division)}
    }

    private fun calculate(operation: (Double, Double) -> Double) {
        val number1 = findViewById<EditText>(R.id.editNumber1).text.toString().toDoubleOrNull()
        val number2 = findViewById<EditText>(R.id.editNumber2).text.toString().toDoubleOrNull();
        val viewResult = findViewById<TextView>(R.id.textViewResult)

        if (number1 == null || number2 == null) {
            viewResult.text = "Existem campos vazios";
            return
        }

        viewResult.text = mathematicalOperation(number1, number2, operation).toString();
    }

    private fun mathematicalOperation(a: Double, b: Double, operation: (Double, Double) -> Double): Double {
        return operation(a, b)
    }
}


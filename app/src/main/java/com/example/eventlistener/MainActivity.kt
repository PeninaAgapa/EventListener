package com.example.eventlistener

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var numberWeight: EditText
    private lateinit var numberHeight: EditText
    private lateinit var rbUSC: RadioButton
    private lateinit var btnCalculate: Button
    private lateinit var textBMIResult: TextView
    private lateinit var textBMIInterpretation: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        numberWeight = findViewById(R.id.numberWeight)
        numberHeight = findViewById(R.id.numberHeight)
        rbUSC = findViewById(R.id.rbUSC)
        btnCalculate = findViewById(R.id.btnCalculate)
        textBMIResult = findViewById(R.id.textBMIResult)
        textBMIInterpretation = findViewById(R.id.textBMIInterpretation)

        // Calculate button click listener
        btnCalculate.setOnClickListener {
            calculateBMI()
        }
    }

    private fun calculateBMI() {
        val weight = numberWeight.text.toString().toFloat()
        val height = numberHeight.text.toString().toFloat()

        // Calculate BMI based on unit selection
        val bmi: Float = if (rbUSC.isChecked) {
            // USC (lbs/in) formula: BMI = weight (lbs) / (height (in) * height (in)) * 703
            (weight / (height * height)) * 703
        } else {
            // Metric (kg/m) formula: BMI = weight (kg) / (height (m) * height (m))
            weight / (height * height)
        }

        displayBMIResult(bmi)
    }

    @SuppressLint("SetTextI18n")
    private fun displayBMIResult(bmi: Float) {
        // Display BMI value
        textBMIResult.text = "BMI: $bmi"

        // Display interpretation
        val interpretation = interpretBMI(bmi)
        textBMIInterpretation.text = "Interpretation: $interpretation"
    }

    private fun interpretBMI(bmi: Float): String {
        return when {
            bmi < 18.5 -> "Underweight"
            bmi >= 18.5 && bmi < 25 -> "Normal weight"
            bmi >= 25 && bmi < 30 -> "Overweight"
            else -> "Obese"
        }
    }
}

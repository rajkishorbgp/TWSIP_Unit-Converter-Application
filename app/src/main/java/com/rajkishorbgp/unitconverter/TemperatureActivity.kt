package com.rajkishorbgp.unitconverter

//noinspection SuspiciousImport
import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.rajkishorbgp.unitconverter.databinding.ActivityTemperatureBinding
import java.text.DecimalFormat

class TemperatureActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTemperatureBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTemperatureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val areaUnits = arrayOf(
            "Celsius", "Kelvin", "Fahrenheit"
        )
        val arrayAdapter = ArrayAdapter(this, R.layout.simple_spinner_dropdown_item, areaUnits)
        binding.spinnerFrom.adapter = arrayAdapter
        binding.spinnerTo.adapter = arrayAdapter

        binding.convertButton.setOnClickListener { convertArea() }
    }

    private fun convertArea() {
        val inputAreaText = binding.areaInput.text.toString()
        if (inputAreaText.isBlank()) {
            "Please enter a valid numeric value".showToast()
            return
        }

        val inputArea = inputAreaText.toDoubleOrNull()
        if (inputArea == null) {
            "Please enter a valid numeric value".showToast()
            return
        }

        val fromUnitPosition = binding.spinnerFrom.selectedItemPosition
        val toUnitPosition = binding.spinnerTo.selectedItemPosition

        val conversionResult = calculateConversion(inputArea, toUnitPosition, fromUnitPosition)
        displayResult(conversionResult)
    }

    private fun calculateConversion(value: Double, fromUnit: Int, toUnit: Int): Double {

        var meter=0.0
        when (fromUnit) {
            0 -> { // Celsius
                meter=value
            }
            1 -> { //Kelvin
                meter=value*0.001
            }
            2 ->{//Fahrenheit

            }
        }
        return calculateResult(meter,toUnit)
    }

    private fun calculateResult(meter: Double, toUnit: Int): Double {
        when (toUnit) {
            0 -> { // Celsius
                return meter
            }
            1 -> { //Kelvin
                return meter/0.001
            }
            2 -> { // Fahrenheit
                return meter/100
            }
        }
        "select the current option".showToast()
        return 0.0
    }

    private fun displayResult(result: Double) {
        val resultText = DecimalFormat("#.#####").format(result)
        binding.conversionResult.text = resultText
        binding.conversionResult.visibility = View.VISIBLE
    }

    private fun String.showToast() {
        Toast.makeText(this@TemperatureActivity, this, Toast.LENGTH_SHORT).show()
    }
}
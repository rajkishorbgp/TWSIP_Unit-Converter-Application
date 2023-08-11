package com.rajkishorbgp.unitconverter

//noinspection SuspiciousImport
import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.rajkishorbgp.unitconverter.databinding.ActivityFrequencyBinding
import java.text.DecimalFormat

class FrequencyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFrequencyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFrequencyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.title = "Convert Length"

        val areaUnits = arrayOf(
            "Cubic Meter", "Cubic Kilometer", "Cubic Centimeter", "Cubic Millimeter",
            "Liter", "Milliliter", "US Gallon", "Us Quart","US Pint", "US Cup", "US Fluid Ounce"

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
            0 -> { // meter
                meter=value
            }
            1 -> { //kilometer
                meter=value*0.001
            }
            2 -> { // centimeter
                meter=value*100
            }
            3 -> {//micrometer
                meter=value*1000000
            }
            4 -> {//nanometer
                meter=value*1000000000
            }
            5 -> {//mile
                meter=value*0.0006213689
            }
            6 -> {//Yard
                meter=value*1.0936132983
            }
            7 -> {//foot
                meter=value*3.280839895
            }
            8 -> {// inch
                meter=value*39.37007874
            }
        }
        return calculateResult(meter,toUnit)
    }

    private fun calculateResult(meter: Double, toUnit: Int): Double {
        when (toUnit) {
            0 -> { // meter
                return meter
            }
            1 -> { //kilometer
                return meter/0.001
            }
            2 -> { // centimeter
                return meter/100
            }
            3 -> {//micrometer
                return meter/1000000
            }
            4 -> {//nanometer
                return meter/1000000000
            }
            5 -> {//mile
                return meter/0.0006213689
            }
            6 -> {//Yard
                return meter/1.0936132983
            }
            7 -> {//foot
                return meter/3.280839895
            }
            8 -> {// inch
                return meter/39.37007874
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
        Toast.makeText(this@FrequencyActivity, this, Toast.LENGTH_SHORT).show()
    }
}
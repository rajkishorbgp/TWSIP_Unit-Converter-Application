package com.rajkishorbgp.unitconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.rajkishorbgp.unitconverter.databinding.ActivityLengthBinding
import java.text.DecimalFormat

@Suppress("DEPRECATION")
class LengthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLengthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLengthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.title = "Length Convert"
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val lengthUnits = arrayOf(
            "Meter", "Kilometer", "Centimeter", "Micrometer",
            "Nanometer", "Mile", "Yard", "Foot", "Inch"
        )
        val arrayAdapter = ArrayAdapter(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, lengthUnits)
        binding.spinnerFrom.adapter = arrayAdapter
        binding.spinnerTo.adapter = arrayAdapter

        binding.convertButton.setOnClickListener { convertLength() }
    }

    private fun convertLength() {
        val inputLengthText = binding.lengthInput.text.toString()
        if (inputLengthText.isBlank()) {
            "Please enter a valid numeric value".showToast()
            return
        }

        val inputLength = inputLengthText.toDoubleOrNull()
        if (inputLength == null) {
            "Please enter a valid numeric value".showToast()
            return
        }

        val fromUnitPosition = binding.spinnerFrom.selectedItemPosition
        val toUnitPosition = binding.spinnerTo.selectedItemPosition

        val conversionResult = calculateConversion(inputLength, toUnitPosition, fromUnitPosition)
        displayResult(conversionResult)
    }

    private fun calculateConversion(value: Double, fromUnit: Int, toUnit: Int): Double {
        var meter = 0.0
        when (fromUnit) {
            0 -> meter = value // meter
            1 -> meter = value * 0.001 // kilometer
            2 -> meter = value * 100 // centimeter
            3 -> meter = value * 1000000 // micrometer
            4 -> meter = value * 1000000000 // nanometer
            5 -> meter = value * 0.0006213689 // mile
            6 -> meter = value * 1.0936132983 // yard
            7 -> meter = value * 3.280839895 // foot
            8 -> meter = value * 39.37007874 // inch
        }
        return calculateResult(meter, toUnit)
    }

    private fun calculateResult(meter: Double, toUnit: Int): Double {
        when (toUnit) {
            0 -> return meter // meter
            1 -> return meter / 0.001 // kilometer
            2 -> return meter / 100 // centimeter
            3 -> return meter / 1000000 // micrometer
            4 -> return meter / 1000000000 // nanometer
            5 -> return meter / 0.0006213689 // mile
            6 -> return meter / 1.0936132983 // yard
            7 -> return meter / 3.280839895 // foot
            8 -> return meter / 39.37007874 // inch
        }
        "Select the current option".showToast()
        return 0.0
    }

    private fun displayResult(result: Double) {
        val resultText = DecimalFormat("#.#####").format(result)
        binding.conversionResult.text = resultText
        binding.conversionResult.visibility = View.VISIBLE
    }

    private fun String.showToast() {
        Toast.makeText(this@LengthActivity, this, Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}

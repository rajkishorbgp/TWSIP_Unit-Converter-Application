package com.rajkishorbgp.unitconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.rajkishorbgp.unitconverter.databinding.ActivityMassBinding
import java.text.DecimalFormat

@Suppress("DEPRECATION")
class MassActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMassBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMassBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.title = "Mass Convert"
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val massUnits = arrayOf(
            "Kilogram", "Gram", "Metric Ton", "Long Ton",
            "Short Ton", "Pound", "Ounce", "Carrat", "Atomic Mass Unit"
        )
        val arrayAdapter = ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, massUnits)
        binding.spinnerFrom.adapter = arrayAdapter
        binding.spinnerTo.adapter = arrayAdapter

        binding.convertButton.setOnClickListener { convertMass() }
    }

    private fun convertMass() {
        val inputMassText = binding.massInput.text.toString()
        if (inputMassText.isBlank()) {
            "Please enter a valid numeric value".showToast()
            return
        }

        val inputMass = inputMassText.toDoubleOrNull()
        if (inputMass == null) {
            "Please enter a valid numeric value".showToast()
            return
        }

        val fromUnitPosition = binding.spinnerFrom.selectedItemPosition
        val toUnitPosition = binding.spinnerTo.selectedItemPosition

        val conversionResult = calculateConversion(inputMass, toUnitPosition, fromUnitPosition)
        displayResult(conversionResult)
    }

    private fun calculateConversion(value: Double, fromUnit: Int, toUnit: Int): Double {
        // Implement your conversion logic here for each unit type
        when (fromUnit) {
            0 -> {
                return calculateKilogramToOther(value, toUnit)
            }
            1 -> {
                return calculateGramToOther(value, toUnit)
            }
            2 -> {
                return calculateMetricTonToOther(value, toUnit)
            }
            3 -> {
                return calculateLongTonToOther(value, toUnit)
            }
            4 -> {
                return calculateShortTonToOther(value, toUnit)
            }
            5 -> {
                return calculatePoundToOther(value, toUnit)
            }
            6 -> {
                return calculateOunceToOther(value, toUnit)
            }
            7 -> {
                return calculateCaratToOther(value, toUnit)
            }
            8 -> {
                return calculateAtomicMassUnitToOther(value, toUnit)
            }
            else -> {
                return 0.0 // Default placeholder
            }
        }
    }

    private fun calculateKilogramToOther(value: Double, toUnit: Int): Double {
        return when (toUnit) {
            0 -> value // Kilogram to Kilogram
            1 -> value * 1000 // Kilogram to Gram
            2 -> value * 0.001 // Kilogram to Metric Ton
            3 -> value * 0.000984207 // Kilogram to Long Ton
            4 -> value * 0.00110231 // Kilogram to Short Ton
            5 -> value * 2.20462 // Kilogram to Pound
            6 -> value * 35.27396 // Kilogram to Ounce
            7 -> value * 5000 // Kilogram to Carat
            8 -> value * 6.02214e+26 // Kilogram to Atomic Mass Unit
            else -> 0.0 // Default placeholder
        }
    }

    private fun calculateGramToOther(value: Double, toUnit: Int): Double {
        return when (toUnit) {
            0 -> value * 0.001 // Gram to Kilogram
            1 -> value // Gram to Gram
            2 -> value * 1e-6 // Gram to Metric Ton
            3 -> value * 9.8421e-7 // Gram to Long Ton
            4 -> value * 0.00000110231 // Gram to Short Ton
            5 -> value * 0.00220462 // Gram to Pound
            6 -> value * 0.03527396 // Gram to Ounce
            7 -> value * 0.2 // Gram to Carat
            8 -> value * 6.02214e+23 // Gram to Atomic Mass Unit
            else -> 0.0 // Default placeholder
        }
    }

    private fun calculateMetricTonToOther(value: Double, toUnit: Int): Double {
        return when (toUnit) {
            0 -> value * 1000 // Metric Ton to Kilogram
            1 -> value * 1e+6 // Metric Ton to Gram
            2 -> value // Metric Ton to Metric Ton
            3 -> value * 0.984207 // Metric Ton to Long Ton
            4 -> value * 1.10231 // Metric Ton to Short Ton
            5 -> value * 2204.62 // Metric Ton to Pound
            6 -> value * 35273.96 // Metric Ton to Ounce
            7 -> value * 500000 // Metric Ton to Carat
            8 -> value * 6.02214e+29 // Metric Ton to Atomic Mass Unit
            else -> 0.0 // Default placeholder
        }
    }

    private fun calculateLongTonToOther(value: Double, toUnit: Int): Double {
        return when (toUnit) {
            0 -> value * 1016.05 // Long Ton to Kilogram
            1 -> value * 1.01605e+6 // Long Ton to Gram
            2 -> value * 1016.05 // Long Ton to Metric Ton
            3 -> value // Long Ton to Long Ton
            4 -> value * 1.12 // Long Ton to Short Ton
            5 -> value * 2240 // Long Ton to Pound
            6 -> value * 35840 // Long Ton to Ounce
            7 -> value * 50802.3 // Long Ton to Carat
            8 -> value * 6.02214e+29 // Long Ton to Atomic Mass Unit
            else -> 0.0 // Default placeholder
        }
    }

    private fun calculateShortTonToOther(value: Double, toUnit: Int): Double {
        return when (toUnit) {
            0 -> value * 907.185 // Short Ton to Kilogram
            1 -> value * 907185 // Short Ton to Gram
            2 -> value * 907.185 // Short Ton to Metric Ton
            3 -> value * 0.892857 // Short Ton to Long Ton
            4 -> value // Short Ton to Short Ton
            5 -> value * 2000 // Short Ton to Pound
            6 -> value * 32000 // Short Ton to Ounce
            7 -> value * 453592 // Short Ton to Carat
            8 -> value * 6.02214e+29 // Short Ton to Atomic Mass Unit
            else -> 0.0 // Default placeholder
        }
    }

    private fun calculatePoundToOther(value: Double, toUnit: Int): Double {
        return when (toUnit) {
            0 -> value * 0.453592 // Pound to Kilogram
            1 -> value * 453.592 // Pound to Gram
            2 -> value * 0.000453592 // Pound to Metric Ton
            3 -> value * 0.000446429 // Pound to Long Ton
            4 -> value * 0.0005 // Pound to Short Ton
            5 -> value // Pound to Pound
            6 -> value * 16 // Pound to Ounce
            7 -> value * 2267.96 // Pound to Carat
            8 -> value * 2.7316e+26 // Pound to Atomic Mass Unit
            else -> 0.0 // Default placeholder
        }
    }

    private fun calculateOunceToOther(value: Double, toUnit: Int): Double {
        return when (toUnit) {
            0 -> value * 0.0283495 // Ounce to Kilogram
            1 -> value * 28.3495 // Ounce to Gram
            2 -> value * 0.0000283495 // Ounce to Metric Ton
            3 -> value * 0.0000279018 // Ounce to Long Ton
            4 -> value * 0.00003125 // Ounce to Short Ton
            5 -> value * 0.0625 // Ounce to Pound
            6 -> value // Ounce to Ounce
            7 -> value * 141.748 // Ounce to Carat
            8 -> value * 1.7072e+25 // Ounce to Atomic Mass Unit
            else -> 0.0 // Default placeholder
        }
    }

    private fun calculateCaratToOther(value: Double, toUnit: Int): Double {
        return when (toUnit) {
            0 -> value * 0.0002 // Carat to Kilogram
            1 -> value * 0.2 // Carat to Gram
            2 -> value * 2e-7 // Carat to Metric Ton
            3 -> value * 1.9685e-7 // Carat to Long Ton
            4 -> value * 2.20462e-7 // Carat to Short Ton
            5 -> value * 0.000440925 // Carat to Pound
            6 -> value * 0.00705479 // Carat to Ounce
            7 -> value // Carat to Carat
            8 -> value * 1.20443e+23 // Carat to Atomic Mass Unit
            else -> 0.0 // Default placeholder
        }
    }

    private fun calculateAtomicMassUnitToOther(value: Double, toUnit: Int): Double {
        return when (toUnit) {
            0 -> value * 1.66054e-27 // Atomic Mass Unit to Kilogram
            1 -> value * 1.66054e-24 // Atomic Mass Unit to Gram
            2 -> value * 1.66054e-30 // Atomic Mass Unit to Metric Ton
            3 -> value * 1.62751e-30 // Atomic Mass Unit to Long Ton
            4 -> value * 1.8099e-30 // Atomic Mass Unit to Short Ton
            5 -> value * 3.66086e-27 // Atomic Mass Unit to Pound
            6 -> value * 5.85773e-26 // Atomic Mass Unit to Ounce
            7 -> value * 4.1840000000000004E22 // Atomic Mass Unit to Carat
            8 -> value // Atomic Mass Unit to Atomic Mass Unit
            else -> 0.0 // Default placeholder
        }
    }

    private fun displayResult(result: Double) {
        val resultText = DecimalFormat("#.#####").format(result)
        binding.conversionResult.text = resultText
        binding.conversionResult.visibility = View.VISIBLE
    }

    private fun String.showToast() {
        Toast.makeText(this@MassActivity, this, Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}
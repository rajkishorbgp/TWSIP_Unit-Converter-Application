package com.rajkishorbgp.unitconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.rajkishorbgp.unitconverter.databinding.ActivityFrequencyBinding
import java.text.DecimalFormat

@Suppress("DEPRECATION")
class FrequencyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFrequencyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFrequencyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.title = "Frequency Convert"
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val frequencyUnits = arrayOf(
            "Hertz", "Kilohertz", "Megahertz", "Gigahertz", "Terahertz"
        )
        val arrayAdapter = ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, frequencyUnits)
        binding.spinnerFrom.adapter = arrayAdapter
        binding.spinnerTo.adapter = arrayAdapter

        binding.convertButton.setOnClickListener { convertFrequency() }
    }

    private fun convertFrequency() {
        val inputFrequencyText = binding.frequencyInput.text.toString()
        if (inputFrequencyText.isBlank()) {
            "Please enter a valid numeric value".showToast()
            return
        }

        val inputFrequency = inputFrequencyText.toDoubleOrNull()
        if (inputFrequency == null) {
            "Please enter a valid numeric value".showToast()
            return
        }

        val fromUnitPosition = binding.spinnerFrom.selectedItemPosition
        val toUnitPosition = binding.spinnerTo.selectedItemPosition

        val conversionResult = calculateConversion(inputFrequency, toUnitPosition, fromUnitPosition)
        displayResult(conversionResult)
    }

    private fun calculateConversion(value: Double, fromUnit: Int, toUnit: Int): Double {
        // Implement your conversion logic here for each unit type
        when (fromUnit) {
            0 -> {
                return calculateHertzToOther(value, toUnit)
            }
            1 -> {
                return calculateKilohertzToOther(value, toUnit)
            }
            2 -> {
                return calculateMegahertzToOther(value, toUnit)
            }
            3 -> {
                return calculateGigahertzToOther(value, toUnit)
            }
            4 -> {
                return calculateTerahertzToOther(value, toUnit)
            }
            else -> {
                return 0.0 // Default placeholder
            }
        }
    }

    private fun calculateHertzToOther(value: Double, toUnit: Int): Double {
        return when (toUnit) {
            0 -> value // Hertz to Hertz
            1 -> value * 0.001 // Hertz to Kilohertz
            2 -> value * 1e-6 // Hertz to Megahertz
            3 -> value * 1e-9 // Hertz to Gigahertz
            4 -> value * 1e-12 // Hertz to Terahertz
            else -> 0.0 // Default placeholder
        }
    }

    private fun calculateKilohertzToOther(value: Double, toUnit: Int): Double {
        return when (toUnit) {
            0 -> value * 1000 // Kilohertz to Hertz
            1 -> value // Kilohertz to Kilohertz
            2 -> value * 0.001 // Kilohertz to Megahertz
            3 -> value * 1e-6 // Kilohertz to Gigahertz
            4 -> value * 1e-9 // Kilohertz to Terahertz
            else -> 0.0 // Default placeholder
        }
    }

    private fun calculateMegahertzToOther(value: Double, toUnit: Int): Double {
        return when (toUnit) {
            0 -> value * 1e6 // Megahertz to Hertz
            1 -> value * 1000 // Megahertz to Kilohertz
            2 -> value // Megahertz to Megahertz
            3 -> value * 1e-3 // Megahertz to Gigahertz
            4 -> value * 1e-6 // Megahertz to Terahertz
            else -> 0.0 // Default placeholder
        }
    }

    private fun calculateGigahertzToOther(value: Double, toUnit: Int): Double {
        return when (toUnit) {
            0 -> value * 1e9 // Gigahertz to Hertz
            1 -> value * 1e6 // Gigahertz to Kilohertz
            2 -> value * 1000 // Gigahertz to Megahertz
            3 -> value // Gigahertz to Gigahertz
            4 -> value * 1e-3 // Gigahertz to Terahertz
            else -> 0.0 // Default placeholder
        }
    }

    private fun calculateTerahertzToOther(value: Double, toUnit: Int): Double {
        return when (toUnit) {
            0 -> value * 1e12 // Terahertz to Hertz
            1 -> value * 1e9 // Terahertz to Kilohertz
            2 -> value * 1e6 // Terahertz to Megahertz
            3 -> value * 1000 // Terahertz to Gigahertz
            4 -> value // Terahertz to Terahertz
            else -> 0.0 // Default placeholder
        }
    }

    private fun displayResult(result: Double) {
        val resultText = DecimalFormat("#.#####").format(result)
        binding.conversionResult.text = resultText
        binding.conversionResult.visibility = View.VISIBLE
    }

    private fun String.showToast() {
        Toast.makeText(this@FrequencyActivity, this, Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}

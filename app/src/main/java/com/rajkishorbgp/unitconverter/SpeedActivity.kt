package com.rajkishorbgp.unitconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.rajkishorbgp.unitconverter.databinding.ActivitySpeedBinding
import java.text.DecimalFormat

@Suppress("DEPRECATION")
class SpeedActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySpeedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpeedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.title = "Speed Convert"
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val speedUnits = arrayOf(
            "Meter/Second", "Kilometer/Hour", "Meter/Hour", "Knot",
            "Foot/Second", "Mile/Hour"
        )
        val arrayAdapter = ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, speedUnits)
        binding.spinnerFrom.adapter = arrayAdapter
        binding.spinnerTo.adapter = arrayAdapter

        binding.convertButton.setOnClickListener { convertSpeed() }
    }

    private fun convertSpeed() {
        val inputSpeedText = binding.speedInput.text.toString()
        if (inputSpeedText.isBlank()) {
            "Please enter a valid numeric value".showToast()
            return
        }

        val inputSpeed = inputSpeedText.toDoubleOrNull()
        if (inputSpeed == null) {
            "Please enter a valid numeric value".showToast()
            return
        }

        val fromUnitPosition = binding.spinnerFrom.selectedItemPosition
        val toUnitPosition = binding.spinnerTo.selectedItemPosition

        val conversionResult = calculateConversion(inputSpeed, toUnitPosition, fromUnitPosition)
        displayResult(conversionResult)
    }

    private fun calculateConversion(value: Double, fromUnit: Int, toUnit: Int): Double {
        // Implement your conversion logic here for each unit type
        when (fromUnit) {
            0 -> {
                return calculateMeterPerSecondToOther(value, toUnit)
            }
            1 -> {
                return calculateKilometerPerHourToOther(value, toUnit)
            }
            2 -> {
                return calculateMeterPerHourToOther(value, toUnit)
            }
            3 -> {
                return calculateKnotToOther(value, toUnit)
            }
            4 -> {
                return calculateFootPerSecondToOther(value, toUnit)
            }
            5 -> {
                return calculateMilePerHourToOther(value, toUnit)
            }
            else -> {
                return 0.0 // Default placeholder
            }
        }
    }

    private fun calculateMeterPerSecondToOther(value: Double, toUnit: Int): Double {
        return when (toUnit) {
            0 -> value // Meter/Second to Meter/Second
            1 -> value * 3.6 // Meter/Second to Kilometer/Hour
            2 -> value * 3600 // Meter/Second to Meter/Hour
            3 -> value * 1.94384 // Meter/Second to Knot
            4 -> value * 3.28084 // Meter/Second to Foot/Second
            5 -> value * 2.23694 // Meter/Second to Mile/Hour
            else -> 0.0 // Default placeholder
        }
    }

    private fun calculateKilometerPerHourToOther(value: Double, toUnit: Int): Double {
        return when (toUnit) {
            0 -> value * 0.277778 // Kilometer/Hour to Meter/Second
            1 -> value // Kilometer/Hour to Kilometer/Hour
            2 -> value * 1000 // Kilometer/Hour to Meter/Hour
            3 -> value * 0.539957 // Kilometer/Hour to Knot
            4 -> value * 0.911344 // Kilometer/Hour to Foot/Second
            5 -> value * 0.621371 // Kilometer/Hour to Mile/Hour
            else -> 0.0 // Default placeholder
        }
    }

    private fun calculateMeterPerHourToOther(value: Double, toUnit: Int): Double {
        return when (toUnit) {
            0 -> value * 0.000277778 // Meter/Hour to Meter/Second
            1 -> value * 0.000277778 * 1000 // Meter/Hour to Kilometer/Hour
            2 -> value // Meter/Hour to Meter/Hour
            3 -> value * 0.000277778 * 0.539957 // Meter/Hour to Knot
            4 -> value * 0.000277778 * 0.911344 // Meter/Hour to Foot/Second
            5 -> value * 0.000277778 * 0.621371 // Meter/Hour to Mile/Hour
            else -> 0.0 // Default placeholder
        }
    }

    private fun calculateKnotToOther(value: Double, toUnit: Int): Double {
        return when (toUnit) {
            0 -> value * 0.514444 // Knot to Meter/Second
            1 -> value * 0.514444 * 3.6 // Knot to Kilometer/Hour
            2 -> value * 514.444 // Knot to Meter/Hour
            3 -> value // Knot to Knot
            4 -> value * 1.68781 // Knot to Foot/Second
            5 -> value * 1.15078 // Knot to Mile/Hour
            else -> 0.0 // Default placeholder
        }
    }

    private fun calculateFootPerSecondToOther(value: Double, toUnit: Int): Double {
        return when (toUnit) {
            0 -> value * 0.3048 // Foot/Second to Meter/Second
            1 -> value * 0.3048 * 3.6 // Foot/Second to Kilometer/Hour
            2 -> value * 0.3048 * 3600 // Foot/Second to Meter/Hour
            3 -> value * 0.3048 * 0.539957 // Foot/Second to Knot
            4 -> value // Foot/Second to Foot/Second
            5 -> value * 0.681818 // Foot/Second to Mile/Hour
            else -> 0.0 // Default placeholder
        }
    }

    private fun calculateMilePerHourToOther(value: Double, toUnit: Int): Double {
        return when (toUnit) {
            0 -> value * 0.44704 // Mile/Hour to Meter/Second
            1 -> value * 0.44704 * 3.6 // Mile/Hour to Kilometer/Hour
            2 -> value * 0.44704 * 3600 // Mile/Hour to Meter/Hour
            3 -> value * 0.44704 * 0.539957 // Mile/Hour to Knot
            4 -> value * 0.44704 * 1.467 // Mile/Hour to Foot/Second
            5 -> value // Mile/Hour to Mile/Hour
            else -> 0.0 // Default placeholder
        }
    }

    private fun displayResult(result: Double) {
        val resultText = DecimalFormat("#.#####").format(result)
        binding.conversionResult.text = resultText
        binding.conversionResult.visibility = View.VISIBLE
    }

    private fun String.showToast() {
        Toast.makeText(this@SpeedActivity, this, Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}

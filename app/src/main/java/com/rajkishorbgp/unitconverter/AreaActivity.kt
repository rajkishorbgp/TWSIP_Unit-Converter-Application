package com.rajkishorbgp.unitconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.rajkishorbgp.unitconverter.databinding.ActivityAreaBinding
import java.text.DecimalFormat

@Suppress("DEPRECATION")
class AreaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAreaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAreaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.title = "Area Convert"
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val areaUnits = arrayOf(
            "Square Meter", "Square Kilometer", "Square Centimeter","Square Millimeter", "Square Micrometer",
            "Hectare", "Square Mile", "Square Yard", "Square Foot","Square Inch"
        )
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, areaUnits)
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

        val conversionResult = calculateConversion(inputArea, fromUnitPosition, toUnitPosition)
        displayResult(conversionResult)
    }

    private fun calculateConversion(value: Double, fromUnit: Int, toUnit: Int): Double {
        return when (fromUnit) {
            0 -> calculateSquareMeterToOther(value, toUnit) // Square Meter
            1 -> calculateSquareKilometerToOther(value, toUnit) // Square Kilometer
            2 -> calculateSquareCentimeterToOther(value, toUnit) // Square Centimeter
            3 -> calculateSquareMillimeterToOther(value, toUnit) // Square Millimeter
            4 -> calculateSquareMicrometerToOther(value, toUnit) // Square Micrometer
            5 -> calculateHectareToOther(value, toUnit) // Hectare
            6 -> calculateSquareMileToOther(value, toUnit) // Square Mile
            7 -> calculateSquareYardToOther(value, toUnit) // Square Yard
            8 -> calculateSquareFootToOther(value, toUnit) // Square Foot
            9 -> calculateSquareInchToOther(value, toUnit) // Square Inch
            else -> 0.0
        }
    }


    private fun calculateSquareMeterToOther(squareMeter: Double, toUnit: Int): Double {
        return when (toUnit) {
            0 -> squareMeter // Square Meter to Square Meter
            1 -> squareMeter * 1e-6 // Square Meter to Square Kilometer
            2 -> squareMeter * 10000.0 // Square Meter to Square Centimeter
            3 -> squareMeter * 1e+6 // Square Meter to Square Millimeter
            4 -> squareMeter * 1e+12 // Square Meter to Square Micrometer
            5 -> squareMeter * 0.0001 // Square Meter to Hectare
            6 -> squareMeter * 3.861e-7 // Square Meter to Square Mile
            7 -> squareMeter * 1.19599 // Square Meter to Square Yard
            8 -> squareMeter * 10.7639 // Square Meter to Square Foot
            9 -> squareMeter * 1550.0031 // Square Meter to Square Inch
            else -> 0.0
        }
    }

    private fun calculateSquareKilometerToOther(squareKilometer: Double, toUnit: Int): Double {
        return when (toUnit) {
            0 -> squareKilometer * 1e+6 // Square Kilometer to Square Meter
            1 -> squareKilometer // Square Kilometer to Square Kilometer
            2 -> squareKilometer * 1e+10 // Square Kilometer to Square Centimeter
            3 -> squareKilometer * 1e+12 // Square Kilometer to Square Millimeter
            4 -> squareKilometer * 1e+18 // Square Kilometer to Square Micrometer
            5 -> squareKilometer * 100 // Square Kilometer to Hectare
            6 -> squareKilometer * 0.239 // Square Kilometer to Square Mile
            7 -> squareKilometer * 1.196e+6 // Square Kilometer to Square Yard
            8 -> squareKilometer * 1.076e+7 // Square Kilometer to Square Foot
            9 -> squareKilometer * 1.55e+9 // Square Kilometer to Square Inch
            else -> 0.0
        }
    }
    private fun calculateSquareCentimeterToOther(squareCentimeter: Double, toUnit: Int): Double {
        return when (toUnit) {
            0 -> squareCentimeter * 0.0001 // Square Centimeter to Square Meter
            1 -> squareCentimeter * 1e-10 // Square Centimeter to Square Kilometer
            2 -> squareCentimeter // Square Centimeter to Square Centimeter
            3 -> squareCentimeter * 100 // Square Centimeter to Square Millimeter
            4 -> squareCentimeter * 1e+8 // Square Centimeter to Square Micrometer
            5 -> squareCentimeter * 1e-6 // Square Centimeter to Hectare
            6 -> squareCentimeter * 3.861e-11 // Square Centimeter to Square Mile
            7 -> squareCentimeter * 0.000119599 // Square Centimeter to Square Yard
            8 -> squareCentimeter * 0.00107639 // Square Centimeter to Square Foot
            9 -> squareCentimeter * 0.15500031 // Square Centimeter to Square Inch
            else -> 0.0
        }
    }

    private fun calculateSquareMillimeterToOther(squareMillimeter: Double, toUnit: Int): Double {
        return when (toUnit) {
            0 -> squareMillimeter * 1e-6 // Square Millimeter to Square Meter
            1 -> squareMillimeter * 1e-12 // Square Millimeter to Square Kilometer
            2 -> squareMillimeter * 0.01 // Square Millimeter to Square Centimeter
            3 -> squareMillimeter // Square Millimeter to Square Millimeter
            4 -> squareMillimeter * 1e+6 // Square Millimeter to Square Micrometer
            5 -> squareMillimeter * 1e-8 // Square Millimeter to Hectare
            6 -> squareMillimeter * 3.861e-14 // Square Millimeter to Square Mile
            7 -> squareMillimeter * 1.19599e-6 // Square Millimeter to Square Yard
            8 -> squareMillimeter * 1.07639e-5 // Square Millimeter to Square Foot
            9 -> squareMillimeter * 0.0015500031 // Square Millimeter to Square Inch
            else -> 0.0
        }
    }

    private fun calculateSquareMicrometerToOther(squareMicrometer: Double, toUnit: Int): Double {
        return when (toUnit) {
            0 -> squareMicrometer * 1e-12 // Square Micrometer to Square Meter
            1 -> squareMicrometer * 1e-18 // Square Micrometer to Square Kilometer
            2 -> squareMicrometer * 1e-4 // Square Micrometer to Square Centimeter
            3 -> squareMicrometer * 1e-6 // Square Micrometer to Square Millimeter
            4 -> squareMicrometer // Square Micrometer to Square Micrometer
            5 -> squareMicrometer * 1e-14 // Square Micrometer to Hectare
            6 -> squareMicrometer * 3.861e-20 // Square Micrometer to Square Mile
            7 -> squareMicrometer * 1.19599e-10 // Square Micrometer to Square Yard
            8 -> squareMicrometer * 1.07639e-9 // Square Micrometer to Square Foot
            9 -> squareMicrometer * 1.5500031e-7 // Square Micrometer to Square Inch
            else -> 0.0
        }
    }

    private fun calculateHectareToOther(hectare: Double, toUnit: Int): Double {
        return when (toUnit) {
            0 -> hectare * 10000 // Hectare to Square Meter
            1 -> hectare * 0.01 // Hectare to Square Kilometer
            2 -> hectare * 1e+8 // Hectare to Square Centimeter
            3 -> hectare * 1e+10 // Hectare to Square Millimeter
            4 -> hectare * 1e+16 // Hectare to Square Micrometer
            5 -> hectare // Hectare to Hectare
            6 -> hectare * 0.003861 // Hectare to Square Mile
            7 -> hectare * 11959.9 // Hectare to Square Yard
            8 -> hectare * 107639.104 // Hectare to Square Foot
            9 -> hectare * 15500031 // Hectare to Square Inch
            else -> 0.0
        }
    }

    private fun calculateSquareMileToOther(squareMile: Double, toUnit: Int): Double {
        return when (toUnit) {
            0 -> squareMile * 2.58999e+6 // Square Mile to Square Meter
            1 -> squareMile * 2.58999 // Square Mile to Square Kilometer
            2 -> squareMile * 2.58999e+10 // Square Mile to Square Centimeter
            3 -> squareMile * 2.58999e+12 // Square Mile to Square Millimeter
            4 -> squareMile * 2.58999e+18 // Square Mile to Square Micrometer
            5 -> squareMile * 258.9999 // Square Mile to Hectare
            6 -> squareMile // Square Mile to Square Mile
            7 -> squareMile * 3.098e+6 // Square Mile to Square Yard
            8 -> squareMile * 2.78784e+7 // Square Mile to Square Foot
            9 -> squareMile * 4.0144896e+9 // Square Mile to Square Inch
            else -> 0.0
        }
    }

    private fun calculateSquareYardToOther(squareYard: Double, toUnit: Int): Double {
        return when (toUnit) {
            0 -> squareYard * 0.83612736 // Square Yard to Square Meter
            1 -> squareYard * 8.3612736e-7 // Square Yard to Square Kilometer
            2 -> squareYard * 8361.2736 // Square Yard to Square Centimeter
            3 -> squareYard * 836127.36 // Square Yard to Square Millimeter
            4 -> squareYard * 8.3612736e+5 // Square Yard to Square Micrometer
            5 -> squareYard * 8.3612736e-5 // Square Yard to Hectare
            6 -> squareYard * 3.2283069e-7 // Square Yard to Square Mile
            7 -> squareYard // Square Yard to Square Yard
            8 -> squareYard * 9.0000199 // Square Yard to Square Foot
            9 -> squareYard * 1296 // Square Yard to Square Inch
            else -> 0.0
        }
    }

    private fun calculateSquareFootToOther(squareFoot: Double, toUnit: Int): Double {
        return when (toUnit) {
            0 -> squareFoot * 0.09290304 // Square Foot to Square Meter
            1 -> squareFoot * 9.290304e-8 // Square Foot to Square Kilometer
            2 -> squareFoot * 929.0304 // Square Foot to Square Centimeter
            3 -> squareFoot * 92903.04 // Square Foot to Square Millimeter
            4 -> squareFoot * 9.290304e+4 // Square Foot to Square Micrometer
            5 -> squareFoot * 9.290304e-6 // Square Foot to Hectare
            6 -> squareFoot * 3.5870064e-8 // Square Foot to Square Mile
            7 -> squareFoot * 0.11111111 // Square Foot to Square Yard
            8 -> squareFoot // Square Foot to Square Foot
            9 -> squareFoot * 144 // Square Foot to Square Inch
            else -> 0.0
        }
    }


    private fun calculateSquareInchToOther(squareInch: Double, toUnit: Int): Double {
        return when (toUnit) {
            0 -> squareInch * 0.00064516 // Square Inch to Square Meter
            1 -> squareInch * 6.4516e-10 // Square Inch to Square Kilometer
            2 -> squareInch * 6.4516 // Square Inch to Square Centimeter
            3 -> squareInch * 645.16 // Square Inch to Square Millimeter
            4 -> squareInch * 6.4516e+5 // Square Inch to Square Micrometer
            5 -> squareInch * 6.4516e-8 // Square Inch to Hectare
            6 -> squareInch * 2.4909766e-10 // Square Inch to Square Mile
            7 -> squareInch * 0.00077160494 // Square Inch to Square Yard
            8 -> squareInch * 0.0069444445 // Square Inch to Square Foot
            9 -> squareInch // Square Inch to Square Inch
            else -> 0.0
        }
    }


    private fun displayResult(result: Double) {
        val resultText = DecimalFormat("#.#####").format(result)
        binding.conversionResult.text = resultText
        binding.conversionResult.visibility = View.VISIBLE
    }

    private fun String.showToast() {
        Toast.makeText(this@AreaActivity, this, Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}

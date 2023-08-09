package com.rajkishorbgp.unitconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.rajkishorbgp.unitconverter.databinding.ActivityAreaBinding

class AreaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAreaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAreaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val country = arrayOf("Meter","Kilometer","Millimeter","Micrometer","Nanometer","Mile","Yard","Foot","inch","Light Year")

        val arrayAdp = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,country)
        binding.spinnerFrom.adapter=arrayAdp
        binding.spinnerTo.adapter=arrayAdp
    }
}
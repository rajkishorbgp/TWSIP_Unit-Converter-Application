package com.rajkishorbgp.unitconverter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rajkishorbgp.unitconverter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

       binding.areaCardView.setOnClickListener{
           val intent = Intent(this,AreaActivity::class.java)
           startActivity(intent)
       }

        binding.speedCardView.setOnClickListener {
            startActivity(Intent(this,SpeedActivity::class.java))
        }
        binding.lengthCardView.setOnClickListener {
            startActivity(Intent(this,LengthActivity::class.java))
        }
        binding.temperatureCardView.setOnClickListener {
            startActivity(Intent(this,TemperatureActivity::class.java))
        }
        binding.frequencyCardView.setOnClickListener {
            startActivity(Intent(this,FrequencyActivity::class.java))
        }
        binding.massCardView.setOnClickListener {
            startActivity(Intent(this,MassActivity::class.java))
        }


    }
}
package com.rajkishorbgp.unitconverter

import LanguageManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.rajkishorbgp.unitconverter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var languageManager: LanguageManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.title = "Unit Converter"
        binding.toolbar.subtitle = "Home"
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        languageManager = LanguageManager(this)

        binding.areaCardView.setOnClickListener {
            val intent = Intent(this, AreaActivity::class.java)
            startActivity(intent)
        }

        binding.speedCardView.setOnClickListener {
            startActivity(Intent(this, SpeedActivity::class.java))
        }
        binding.lengthCardView.setOnClickListener {
            startActivity(Intent(this, LengthActivity::class.java))
        }
        binding.temperatureCardView.setOnClickListener {
            startActivity(Intent(this, TemperatureActivity::class.java))
        }
        binding.frequencyCardView.setOnClickListener {
            startActivity(Intent(this, FrequencyActivity::class.java))
        }
        binding.massCardView.setOnClickListener {
            startActivity(Intent(this, MassActivity::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.setting) {
            startActivity(Intent(this@MainActivity, SettingActivity::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}

package com.elvanerdem.citydistrict.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.elvanerdem.citydistrict.R

class CityDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_detail)

        if (intent.hasExtra("CityName")) {
            Toast.makeText(this, intent.getStringExtra("CityName"), Toast.LENGTH_SHORT).show()
        }
    }
}
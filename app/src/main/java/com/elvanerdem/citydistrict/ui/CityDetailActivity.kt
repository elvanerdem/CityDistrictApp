package com.elvanerdem.citydistrict.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.elvanerdem.citydistrict.R
import com.elvanerdem.citydistrict.model.City
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.android.synthetic.main.activity_city_detail.*
import timber.log.Timber

class CityDetailActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_detail)

        var city = intent.getStringExtra("City")?.let {  getCityDetail(it) }
        setUpCityDetail(city)



    }

    private fun getCityDetail(jsonStr: String): City? {
        val moshi: Moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<City> = moshi.adapter(City::class.java)
        adapter.fromJson(jsonStr).let { city -> return city }
    }

    private fun setUpCityDetail(city: City?) {
        var districtNameList = city?.districts?.map { it.cityHallName }?.toMutableList()
        //districtNameList?.add(0, "Seçiniz")

        city.let {
            Timber.d("City " + city?.name)
            supportActionBar?.title = city?.name?.capitalize()
            tvPlate.text = city?.plate
            tvAreaCode.text = city?.areaCode
            tvRegion.text = city?.region
            tvCityHall.text = "${city?.cityHallName} BELEDİYESİ"
            tvCityHallPhone.text = "0${city?.cityHallPhone}"
            tvCityHallEmail.text = "${city?.cityHallEMail}"
            tvCityHallWeb.text = "${city?.cityHallWeb}"
            tvInfo.text = "${city?.info}"

            tvCityHallPhone.setOnClickListener {
                val intent = Intent(Intent.ACTION_CALL)
                val uri = Uri.parse("tel:" + "0${city?.districts?.get(0)?.cityHallPhone}")
                intent.data = uri
                startActivity(intent)
            }

            districtNameList?.let { it1 ->
                ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, it1)
            }.also {
                spDistrict.adapter = it
            }


        }

    }
}
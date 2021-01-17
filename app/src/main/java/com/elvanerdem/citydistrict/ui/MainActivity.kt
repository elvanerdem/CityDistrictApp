package com.elvanerdem.citydistrict.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.elvanerdem.citydistrict.R
import com.elvanerdem.citydistrict.adapter.CityListAdapter
import com.elvanerdem.citydistrict.model.Cities
import com.elvanerdem.citydistrict.model.City
import com.elvanerdem.citydistrict.utils.AppUtils
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber


class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName

    private lateinit var cityList: List<City>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getCityList()

        val cityListAdapter = CityListAdapter(cityList)
        rvCityList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = cityListAdapter
        }

        cityListAdapter.setOnItemClickListener(object : CityListAdapter.ClickListener {
            override fun onItemClick(v: View, position: Int) {
                val city = cityListAdapter.getItem(position)
                val intent = Intent(this@MainActivity, CityDetailActivity::class.java)
                intent.putExtra("City", city?.let { getCityDetail(it) })
                startActivity(intent)
            }

        })

    }

    private fun getCityList() {
        val data = AppUtils.loadJSONFromAsset(this, "cities.json")
        val moshi: Moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<Cities> = moshi.adapter(Cities::class.java)
        cityList = adapter.fromJson(data)?.cities ?: mutableListOf()
        Timber.d(TAG + " " + cityList?.get(0).name)
    }

    private fun getCityDetail(city: City): String {
        val moshi: Moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<City> = moshi.adapter(City::class.java)
        return adapter.toJson(city)
    }

}
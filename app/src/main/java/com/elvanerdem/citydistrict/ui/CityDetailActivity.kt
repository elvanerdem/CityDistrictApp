package com.elvanerdem.citydistrict.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.opengl.Visibility
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.elvanerdem.citydistrict.R
import com.elvanerdem.citydistrict.model.City
import com.elvanerdem.citydistrict.utils.REQUEST_PERMISSION_PHONE_CALL
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.android.synthetic.main.activity_city_detail.*
import timber.log.Timber

class CityDetailActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private val TAG = MainActivity::class.java.simpleName

    private lateinit var city: City

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_detail)

        city = intent.getStringExtra("City")?.let {
            getCityDetail(it)
        } ?: run {
            City()
        }

        setUpCityDetail()
    }


    private fun getCityDetail(jsonStr: String): City {
        val moshi: Moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<City> = moshi.adapter(City::class.java)
        return adapter.fromJson(jsonStr) ?: City()
    }

    private fun setUpCityDetail() {
        var districtNameList = city?.districts?.map { it.cityHallName }?.toMutableList()
        districtNameList?.add(0, "Seçiniz")

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
                checkAndRequestPermission()
            }

            districtNameList?.let { it1 ->
                ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, it1)
            }.also {
                spDistrict.adapter = it
                spDistrict.setSelection(0,false)
                spDistrict.onItemSelectedListener = this
            }

        }
    }

    private fun checkAndRequestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            when {
                ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED -> {
                    makeCall()
                } shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE) -> {
                    showAlertDialogForPermission()
                } else -> {
                    requestPermissions(arrayOf(Manifest.permission.CALL_PHONE), REQUEST_PERMISSION_PHONE_CALL)
                }
            }
        }
    }

    private fun makeCall(){
        val intent = Intent(Intent.ACTION_CALL)
        val uri = Uri.parse("tel:${city?.cityHallPhone}")
        intent.data = uri
        startActivity(intent)
    }

    private fun showAlertDialogForPermission(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Gerekli İzin")
        builder.setMessage("Arama yapabilmeniz için izin vermeniz gerekmektedir.")
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        builder.setPositiveButton("Tamam") { dialogInterface, i ->
            dialogInterface.dismiss()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(arrayOf(Manifest.permission.CALL_PHONE), REQUEST_PERMISSION_PHONE_CALL)
            }
        }
        builder.setNegativeButton("İptal") { dialogInterface, i ->
            dialogInterface.dismiss()
        }

        val alertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_PERMISSION_PHONE_CALL -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    makeCall()
                } else {
                    showAlertDialogForPermission()
                }
            }
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        Timber.d(TAG, "onNothingSelected")
    }

    override fun onItemSelected(p0: AdapterView<*>, p1: View?, p2: Int, p3: Long) {
        if (p2 != 0) {
            val selectedDistrictName = spDistrict.adapter.getItem(p2) as String
            val selectedDistrict = city.districts.filter { it.cityHallName.equals(selectedDistrictName, true) }.single()
            if ("" != selectedDistrict.districtName) {
                tvDistrictInfo.text = selectedDistrict.info
                tvDistrictHall.text = "${selectedDistrict.cityHallName.toLowerCase().capitalize()} Belediyesi"
                tvDistrictHallPhone.text = "0${selectedDistrict.cityHallPhone}"
                tvDistrictHallEmail.text = selectedDistrict.cityHallEMail
                tvDistrictHallWeb.text = selectedDistrict.cityHallWeb
                llDistrictDetail.visibility = VISIBLE
                tvDistrictInfo.visibility = VISIBLE
            }
        } else {
            llDistrictDetail.visibility = GONE
            tvDistrictInfo.visibility = GONE
        }
    }
}







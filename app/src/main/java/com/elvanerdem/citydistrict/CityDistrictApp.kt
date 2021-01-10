package com.elvanerdem.citydistrict

import android.app.Application
import timber.log.Timber

class CityDistrictApp: Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
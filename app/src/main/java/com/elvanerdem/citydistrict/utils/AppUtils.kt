package com.elvanerdem.citydistrict.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.nio.charset.Charset
import java.text.DecimalFormat
import java.text.NumberFormat

object AppUtils {

    fun loadJSONFromAsset(context: Context, fileName: String): String {
        var json = ""
        val charset: Charset = Charsets.UTF_8
        try {
            val `is` = context.assets.open(fileName)
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            json = String(buffer, charset)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return ""
        }
        return json
    }

    fun writeJSONToAsset(context: Context, fileName: String, stringData: String) {
        val file = File(context.filesDir, fileName)
        val fileWriter = FileWriter(file)
        val bufferedWriter = BufferedWriter(fileWriter)
        bufferedWriter.write(stringData)
        bufferedWriter.close()
    }


    fun getSharedPrefEditor(context: Context): SharedPreferences.Editor {
        return context.getSharedPreferences(SHARED_PREF_KEY, MODE_PRIVATE).edit()
    }

    fun getTwoDigitNumberFromString(stringNumber: String): String {
        val format: NumberFormat = DecimalFormat("00")
        return format.format(stringNumber.toInt())
    }

}
package com.elvanerdem.citydistrict.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.elvanerdem.citydistrict.R
import com.elvanerdem.citydistrict.model.City
import com.elvanerdem.citydistrict.utils.AppUtils

class CityListAdapter(private val list: List<City>) : RecyclerView.Adapter<CityListAdapter.CityViewHolder>() {

    private var clickListener: ClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CityViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val city: City = list[position]
        holder.bind(city)
    }

    override fun getItemCount(): Int = list.size

    fun getItem(position: Int): City? {
        return list?.get(position)
    }

    fun setOnItemClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }

    inner class CityViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.layout_city_item, parent, false)), View.OnClickListener{
        private var mCitName: TextView? = null
        private var mCityDetail: TextView? = null
        private var mCityPlate: TextView? = null


        init {
            mCitName = itemView.findViewById(R.id.tvCity)
            mCityDetail = itemView.findViewById(R.id.tvCityDetail)
            mCityPlate = itemView.findViewById(R.id.tvPlate)
            if (clickListener != null) {
                itemView.setOnClickListener(this)
            }
        }

        fun bind(city: City) {
            mCitName?.text = city.name.capitalize()
            mCityDetail?.text = "${city.districts.size} İlçe"
            mCityPlate?.text = AppUtils.getTwoDigitNumberFromString(city.plate)
        }

        override fun onClick(v: View?) {
            if (v != null) {
                clickListener?.onItemClick(v,adapterPosition)
            }
        }


    }

    interface ClickListener {
        fun onItemClick(v: View, position: Int)
    }

}







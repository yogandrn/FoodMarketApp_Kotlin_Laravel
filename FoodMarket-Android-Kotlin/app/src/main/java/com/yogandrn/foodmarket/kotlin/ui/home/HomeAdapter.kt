package com.yogandrn.foodmarket.kotlin.ui.home

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yogandrn.foodmarket.kotlin.FoodMarket
import com.yogandrn.foodmarket.kotlin.R
import com.yogandrn.foodmarket.kotlin.model.dummy.HomeModel
import com.yogandrn.foodmarket.kotlin.model.response.home.Data
import kotlinx.android.synthetic.main.food_item_horizontal.view.*


class HomeAdapter (
    private val listData : List<Data>,
    private val itemAdapterCallback : ItemAdapterCallback,
) : RecyclerView.Adapter<HomeAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.food_item_horizontal, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position], itemAdapterCallback)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    class ViewHolder (itemView:View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data : Data, itemAdapterCallback: ItemAdapterCallback) {
            itemView.apply {
                title_food_horizontal.text = data.name
                text_rating.text = data.rate.toString()
                rate_food_horizontal.rating = data.rate?.toFloat() ?: 0f

                Glide.with(context)
                    .load(FoodMarket().imageURL +  data.picturePath)
                    .into(image_food_horizontal)

                itemView.setOnClickListener { itemAdapterCallback.onClick(it, data) }
            }
        }
    }

    interface ItemAdapterCallback {
        fun onClick(v: View, data:Data)
    }

}
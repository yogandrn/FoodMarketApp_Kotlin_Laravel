package com.yogandrn.foodmarket.kotlin.ui.home.recommended

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yogandrn.foodmarket.kotlin.FoodMarket
import com.yogandrn.foodmarket.kotlin.R
import com.yogandrn.foodmarket.kotlin.model.response.home.Data
import com.yogandrn.foodmarket.kotlin.utils.Helpers
import kotlinx.android.synthetic.main.food_item_vertical.view.*


class HomeRecommendedAdapter (
    private val listData : List<Data>,
    private val itemAdapterCallback : ItemAdapterCallback,
) : RecyclerView.Adapter<HomeRecommendedAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.food_item_vertical, parent, false)
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
                title_food_vertical.text = data.name
                price_food_vertical.text = Helpers().formatRupiah(data.price!!)
                detail_rate.rating = data.rate?.toFloat() ?: 0f

                Glide.with(context)
                    .load(FoodMarket().imageURL + data.picturePath)
                    .into(image_food_vertical)

                itemView.setOnClickListener { itemAdapterCallback.onClick(it, data) }
            }
        }
    }

    interface ItemAdapterCallback {
        fun onClick(v: View, data:Data)
    }

}
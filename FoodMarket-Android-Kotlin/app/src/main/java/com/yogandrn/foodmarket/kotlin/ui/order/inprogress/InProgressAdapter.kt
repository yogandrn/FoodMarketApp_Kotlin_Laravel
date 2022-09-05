package com.yogandrn.foodmarket.kotlin.ui.order.inprogress

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yogandrn.foodmarket.kotlin.FoodMarket
import com.yogandrn.foodmarket.kotlin.R
import com.yogandrn.foodmarket.kotlin.model.response.transaction.Data
import com.yogandrn.foodmarket.kotlin.utils.Helpers
import kotlinx.android.synthetic.main.item_order_inprogress.view.*


class InProgressAdapter (
    private val listData : List<Data>,
    private val itemAdapterCallback : ItemAdapterCallback,
) : RecyclerView.Adapter<InProgressAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_order_inprogress, parent, false)
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
                title_food_inprogress.text = data.food.name
                detail_food_inprogress.text = data.quantity.toString() + " items - " + Helpers().formatRupiah(data.total)
                var status : String = ""
                when(data.status) {
                    "ON_PROCESS" -> status = resources.getString(R.string.on_process)
                    "PENDING" -> status = resources.getString(R.string.pending)
                    "ON_DELIVERY" -> status = resources.getString(R.string.on_delivery)
                }
                status_inprogress.text = status

                Glide.with(context)
                    .load(FoodMarket().imageURL +  data.food.picturePath)
                    .into(image_food_inprogress)

                itemView.setOnClickListener { itemAdapterCallback.onClick(it, data) }
            }
        }
    }

    interface ItemAdapterCallback {
        fun onClick(v: View, data:Data)
    }

}
package com.yogandrn.foodmarket.kotlin.ui.order.pastorder

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
import kotlinx.android.synthetic.main.item_order_past.view.*
import java.text.SimpleDateFormat
import java.util.*


class PastOrderAdapter (
    private val listData : List<Data>,
    private val itemAdapterCallback : ItemAdapterCallback,
) : RecyclerView.Adapter<PastOrderAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_order_past, parent, false)
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
                title_food_pastorder.text = data.food.name
                detail_food_pastorder.text = data.quantity.toString() + " items - " + Helpers().formatRupiah(data.total)
//                date_food_pastorder.text = convertLongToTime(data.createdAt)
                date_food_pastorder.text = data.date
//                if (data.status.equals("CANCELLED", true)) {
//                    status_cancel_pastorder.visibility = View.VISIBLE
//                }
                if (data.status == "CANCELLED"){
                    status_cancel_pastorder.text = resources.getString(R.string.cancelled)
                } else {
                    status_cancel_pastorder.text = ""
                }
//                status_cancel_pastorder.text = data.status

                Glide.with(context)
                    .load(FoodMarket().imageURL +  data.food.picturePath)
                    .into(image_food_pastorder)

                itemView.setOnClickListener { itemAdapterCallback.onClick(it, data) }
            }
        }

        fun convertLongToTime(time: Long): String {
            val date = Date(time)
            val format = SimpleDateFormat("yyyy.MM.dd HH:mm")
            return format.format(date)
        }
    }

    interface ItemAdapterCallback {
        fun onClick(v: View, data:Data)
    }

    fun Long.convertToTime(format:String) : String {
        val date = Date(this)
        val formatTanggal = SimpleDateFormat(format)
        return formatTanggal.format(date)
    }

}
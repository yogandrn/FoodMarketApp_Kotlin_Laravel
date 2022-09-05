package com.yogandrn.foodmarket.kotlin.ui.profile

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yogandrn.foodmarket.kotlin.R
import com.yogandrn.foodmarket.kotlin.model.dummy.ProfileMenuModel
import kotlinx.android.synthetic.main.item_profile_menu.view.*


class ProfileMenuAdapter (
    private val listData : List<ProfileMenuModel>,
    private val itemAdapterCallback : ItemAdapterCallback,
) : RecyclerView.Adapter<ProfileMenuAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_profile_menu, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position], itemAdapterCallback)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    class ViewHolder (itemView:View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data : ProfileMenuModel, itemAdapterCallback: ItemAdapterCallback) {
            itemView.apply {
                title_profile_menu.text = data.title


                itemView.setOnClickListener { itemAdapterCallback.onClick(it, data) }
            }
        }
    }

    interface ItemAdapterCallback {
        fun onClick(v: View, data:ProfileMenuModel)
    }

}
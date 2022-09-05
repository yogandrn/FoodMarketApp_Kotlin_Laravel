package com.yogandrn.foodmarket.kotlin.ui.profile.foodmarket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yogandrn.foodmarket.kotlin.R
import com.yogandrn.foodmarket.kotlin.model.dummy.ProfileMenuModel
import com.yogandrn.foodmarket.kotlin.ui.profile.ProfileMenuAdapter
import kotlinx.android.synthetic.main.fragment_profile_account.*

class ProfileFoodFragment : Fragment(), ProfileMenuAdapter.ItemAdapterCallback{

    private var menu : ArrayList<ProfileMenuModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_food, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        initDataDummy()
//        var adapter = ProfileMenuAdapter(menu, this)
//        var layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(activity)
//        rcList.layoutManager = layoutManager
//        rcList.adapter = adapter
    }

    fun initDataDummy() {
        menu.clear()
        menu.add(ProfileMenuModel("Rate App"))
        menu.add(ProfileMenuModel("Help Center"))
        menu.add(ProfileMenuModel("Privacy & Policy"))
        menu.add(ProfileMenuModel("Terms & Conditions"))
    }

    override fun onClick(v: View, data: ProfileMenuModel) {
        Toast.makeText(context, data.title + " is clicked!", Toast.LENGTH_SHORT).show()
    }

}
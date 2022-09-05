package com.yogandrn.foodmarket.kotlin.ui.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.yogandrn.foodmarket.kotlin.model.response.transaction.Data
import com.yogandrn.foodmarket.kotlin.ui.home.newtaste.HomeNewTasteFragment
import com.yogandrn.foodmarket.kotlin.ui.home.popular.HomePopularFragment
import com.yogandrn.foodmarket.kotlin.ui.order.inprogress.InProgressFragment
import com.yogandrn.foodmarket.kotlin.ui.order.pastorder.PastOrderFragment
import com.yogandrn.foodmarket.kotlin.ui.profile.account.ProfileAccountFragment
import com.yogandrn.foodmarket.kotlin.ui.profile.foodmarket.ProfileFoodFragment

class SectionPagerAdapter(fm : FragmentManager) : FragmentStatePagerAdapter(fm) {

    private var inProgressList : ArrayList<Data>? = ArrayList()
    private var pastOrderList : ArrayList<Data>? = ArrayList()

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            0 -> "In Progress"
            1 -> "Past Orders"
            else -> ""
        }
    }
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        var fragment : Fragment
        return when(position) {
            0 -> {
                fragment = InProgressFragment()
                var bundle = Bundle()
                bundle.putParcelableArrayList("data", inProgressList)
                fragment.arguments = bundle
                return fragment
            }
            1 -> {
                fragment =PastOrderFragment()
                var bundle = Bundle()
                bundle.putParcelableArrayList("data", pastOrderList)
                fragment.arguments = bundle
                return fragment
            }
            else -> {
                fragment = InProgressFragment()
                var bundle = Bundle()
                bundle.putParcelableArrayList("data", inProgressList)
                fragment.arguments = bundle
                return fragment
            }
        }
    }

    fun setData(inProgressListParams : ArrayList<Data>?, pastOrderListParams : ArrayList<Data>?,) {
        inProgressList = inProgressListParams
        pastOrderList = pastOrderListParams
    }
}
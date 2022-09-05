package com.yogandrn.foodmarket.kotlin.ui.profile

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.yogandrn.foodmarket.kotlin.ui.home.newtaste.HomeNewTasteFragment
import com.yogandrn.foodmarket.kotlin.ui.profile.account.ProfileAccountFragment
import com.yogandrn.foodmarket.kotlin.ui.profile.foodmarket.ProfileFoodFragment

class SectionPagerAdapter(fm : FragmentManager) : FragmentStatePagerAdapter(fm) {


    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            0 -> "Account"
            1 -> "FoodMarket"
            else -> ""
        }
    }
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> return ProfileAccountFragment()
            1 -> return ProfileFoodFragment()
            else -> return ProfileAccountFragment()
        }
    }
}
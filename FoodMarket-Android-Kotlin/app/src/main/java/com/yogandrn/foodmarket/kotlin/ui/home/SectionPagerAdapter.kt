package com.yogandrn.foodmarket.kotlin.ui.home


//import androidx.fragment.app.Fragment
//import androidx.fragment.app.FragmentManager
//import androidx.fragment.app.FragmentStatePagerAdapter
//import com.yogandrn.foodmarket.kotlin.ui.home.newtaste.HomeNewTasteFragment
//import com.yogandrn.foodmarket.kotlin.ui.home.popular.HomePopularFragment
//import com.yogandrn.foodmarket.kotlin.ui.home.recommended.HomeRecommendedFragment

//class SectionPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
//
//    private val pages = listOf(
//        HomeNewTasteFragment(),
//        HomePopularFragment(),
//        HomeRecommendedFragment(),
//    );
//
//    override fun getPageTitle(position: Int): CharSequence? {
//        return when(position) {
//            0 -> "New Taste"
//            1 -> "Popular"
//            2 -> "Recommended"
//            else -> ""
//        }
//    }
//
//    override fun getCount(): Int {
//        return 3
//    }
//
////    override fun getItem(position: Int): Fragment {
////        return pages[position]
////    }
//
//    override fun getItem(position: Int): Fragment {
//        return when (position) {
//            0 -> return HomeNewTasteFragment()
//            1 -> return HomePopularFragment()
//            2 -> return HomeRecommendedFragment()
//            else -> return HomeNewTasteFragment()
//        }
//
//
//    }
//}


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.yogandrn.foodmarket.kotlin.R
import com.yogandrn.foodmarket.kotlin.model.response.home.Data
import com.yogandrn.foodmarket.kotlin.ui.home.newtaste.HomeNewTasteFragment
import com.yogandrn.foodmarket.kotlin.ui.home.popular.HomePopularFragment
import com.yogandrn.foodmarket.kotlin.ui.home.recommended.HomeRecommendedFragment

class SectionPagerAdapter(fm : FragmentManager) : FragmentStatePagerAdapter(fm) {

    private var newTasteList : ArrayList<Data>? = ArrayList()
    private var popularList : ArrayList<Data>? = ArrayList()
    private var recommendedList : ArrayList<Data>? = ArrayList()


    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            0 -> "Popular"
            1 -> "New Taste"
            2 -> "Recommended"
            else -> ""
        }
    }
    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        var fragment : Fragment
        return when(position) {
            0 -> {
                fragment = HomePopularFragment()
                var bundle = Bundle()
                bundle.putParcelableArrayList("data", popularList)
                fragment.arguments = bundle
                return fragment
            }
            1 -> {

                fragment = HomeNewTasteFragment()
                var bundle = Bundle()
                bundle.putParcelableArrayList("data", newTasteList)
                fragment.arguments = bundle
                return fragment
            }
            2 -> {
                fragment = HomeRecommendedFragment()
                var bundle = Bundle()
                bundle.putParcelableArrayList("data", recommendedList)
                fragment.arguments = bundle
                return fragment
            }
            else -> {
                fragment = HomeNewTasteFragment()
                var bundle = Bundle()
                bundle.putParcelableArrayList("data", newTasteList)
                fragment.arguments = bundle
                return fragment
            }
        }
    }

    fun setData(newTasteListParams: ArrayList<Data>?, popularListParams: ArrayList<Data>?, recommendedListParams: ArrayList<Data>?) {
//        newTasteList?.clear()
//        newTasteList?.addAll(newTasteListParams!!)
//        popularList?.clear()
//        popularList?.addAll(popularListParams!!)
//        recommendedList?.clear()
//        recommendedList?.addAll(recommendedListParams!!)

        newTasteList = newTasteListParams
        popularList = popularListParams
        recommendedList = recommendedListParams
    }
}
package com.yogandrn.foodmarket.kotlin.ui.home

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import com.yogandrn.foodmarket.kotlin.FoodMarket
import com.yogandrn.foodmarket.kotlin.R
import com.yogandrn.foodmarket.kotlin.model.dummy.HomeModel
import com.yogandrn.foodmarket.kotlin.model.dummy.HomeVerticalModel
import com.yogandrn.foodmarket.kotlin.model.response.home.Data
import com.yogandrn.foodmarket.kotlin.model.response.home.HomeResponse
import com.yogandrn.foodmarket.kotlin.model.response.login.User
import com.yogandrn.foodmarket.kotlin.ui.detail.DetailActivity
import com.yogandrn.foodmarket.kotlin.ui.home.HomeAdapter.ItemAdapterCallback
import com.yogandrn.foodmarket.kotlin.ui.home.newtaste.HomeNewTasteAdapter
import com.yogandrn.foodmarket.kotlin.ui.home.newtaste.HomeNewTasteFragment
import com.yogandrn.foodmarket.kotlin.ui.home.popular.HomePopularAdapter
import com.yogandrn.foodmarket.kotlin.ui.home.popular.HomePopularFragment
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.tabLayout
import kotlinx.android.synthetic.main.fragment_home.viewPager
import kotlinx.android.synthetic.main.fragment_order.*

class HomeFragment : Fragment(), HomeAdapter.ItemAdapterCallback, HomeContract.View{

    private var foodList : ArrayList<Data> = ArrayList()
    public var newTasteList : ArrayList<Data> = ArrayList()
    private var popularList : ArrayList<Data>? = ArrayList()
    private var recommendedList : ArrayList<Data>? = ArrayList()
    private lateinit var presenter : HomePresenter
    var progressDialog : Dialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root :View = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initView()

        presenter = HomePresenter(this)
//        if (!foodList.isNullOrEmpty()) {
//            presenter.getHomeItem()
//        }
        presenter.getHomeItem()
        refresh_home.setOnRefreshListener {
            refresh_home.isRefreshing = true
            initView()
            presenter.getHomeItem()
            refresh_home.isRefreshing = false
        }

    }

    override fun onHomeSuccess(homeResponse: HomeResponse) {
        foodList.clear()
        foodList.addAll(homeResponse.data)
        newTasteList = foodList.filter { it.types == "NEW_FOOD" } as ArrayList<Data>
        popularList = foodList.filter { it.types == "POPULAR" } as ArrayList<Data>
        recommendedList = foodList.filter { it.types == "RECOMMENDED" } as ArrayList<Data>
//        for (a in homeResponse.data.indices) {
//            var items:List<String> = homeResponse.data[a].types?.split(",") ?: ArrayList()
//            for (x in items.indices) {
//                if (items[x].equals("new_food", true)) {
//                    newTasteList?.add(homeResponse.data[a])
//                } else if (items[x].equals("recommended", true)) {
//                    recommendedList?.add(homeResponse.data[a])
//                } else if (items[x].equals("popular", true)) {
//                    popularList?.add(homeResponse.data[a])
//                }
//            }
//
//        }

        var homeAdapter = HomeAdapter(homeResponse.data, this)
        var homeLayoutManager : RecyclerView.LayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rv_food_home.layoutManager = homeLayoutManager
        rv_food_home.adapter = homeAdapter

        val sectionPagerAdapter = SectionPagerAdapter(childFragmentManager)
        sectionPagerAdapter.setData(newTasteList, popularList, recommendedList)

        viewPager.adapter = sectionPagerAdapter
        viewPager.currentItem = 0

        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onHomeFailed(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }

    private fun initView() {
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)
        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(R.color.transparent)
        }

        var user = FoodMarket.getApp().getUser()
        var token = FoodMarket.getApp().getToken()
        var userResponse = Gson().fromJson(user, User::class.java)

        Log.v("photo", userResponse.profile_photo_path!!)
        Log.v("token", token.toString())

//        if (!userResponse.profile_photo_url.isNullOrEmpty()) {
            Glide.with(requireActivity()).load(FoodMarket().imageURL + userResponse.profile_photo_path).apply(RequestOptions.circleCropTransform()).into(image_home)
//        }
    }

    override fun onClick(v: View, data: Data) {
        val detail = Intent(context, DetailActivity::class.java)
        detail.putExtra("data", data)
        startActivity(detail)
    }

//
//    override fun onClick(v: View, data: HomeVerticalModel) {
//        Toast.makeText(activity, data.title + " is Clicked!", Toast.LENGTH_SHORT).show()
//    }


//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
}
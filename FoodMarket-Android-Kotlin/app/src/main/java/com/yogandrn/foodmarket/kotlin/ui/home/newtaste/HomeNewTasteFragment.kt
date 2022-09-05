package com.yogandrn.foodmarket.kotlin.ui.home.newtaste

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yogandrn.foodmarket.kotlin.R
import com.yogandrn.foodmarket.kotlin.model.response.home.Data
import com.yogandrn.foodmarket.kotlin.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_home_popular.*

class HomeNewTasteFragment : Fragment() , HomeNewTasteAdapter.ItemAdapterCallback{

    private var newTasteList : ArrayList<Data>? = ArrayList()
    private var progressDialog : Dialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_new_taste, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        initDataDummy()
        newTasteList = arguments?.getParcelableArrayList("data")
        var adapter = HomeNewTasteAdapter(newTasteList!!, this)
        var layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(activity)
        rcList.layoutManager = layoutManager
        rcList.adapter = adapter

        if (!newTasteList.isNullOrEmpty()) {
            rcList.visibility = View.VISIBLE
            text_empty.visibility = View.GONE
        } else {
            text_empty.visibility = View.VISIBLE
        }
    }

//    fun initDataDummy() {
//        foodList.clear()
//        foodList.add(HomeVerticalModel("Ayam Geprek", 18000, "https://resepkoki.id/wp-content/uploads/2017/11/Resep-Ayam-geprek-jogja-1024x956.jpg", 4.7f))
//        foodList.add(HomeVerticalModel("Nasi Goreng", 15000,"https://poshjournal.com/wp-content/uploads/2019/08/nasi-goreng-recipe-indonesian-fried-rice-7-949x1024.jpg", 4.5f))
//        foodList.add(HomeVerticalModel("Rendang", 25000,"https://factsofindonesia.com/wp-content/uploads/2018/01/rendang.jpg", 5f))
//        foodList.add(HomeVerticalModel("Soto Ayam", 15000,"https://factsofindonesia.com/wp-content/uploads/2018/01/rendang.jpg", 5f))
//    }

    override fun onClick(v: View, data: Data) {
        val detail = Intent(context, DetailActivity::class.java)
        detail.putExtra("data", data)
        startActivity(detail)
    }

//    override fun onHomeSuccess(homeResponse: HomeResponse) {
//        var adapter = HomeNewTasteAdapter(homeResponse.data, this)
//        var layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(activity)
//        rcList.layoutManager = layoutManager
//        rcList.adapter = adapter
//
//        if (newTasteList.isNullOrEmpty()) {
//            rcList.visibility = View.GONE
//            text_empty.visibility = View.VISIBLE
//        } else {
//            text_empty.visibility = View.GONE
//        }
//    }
//
//    override fun onHomeFailed(message: String) {
//        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
//    }
//
//    override fun showLoading() {
//        progressDialog?.show()
//    }
//
//    override fun dismissLoading() {
//        progressDialog?.dismiss()
//    }

    private fun initView() {
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_circular, null)
        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(R.color.transparent)
        }
    }

}
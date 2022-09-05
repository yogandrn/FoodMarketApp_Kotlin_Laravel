package com.yogandrn.foodmarket.kotlin.ui.home.popular

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yogandrn.foodmarket.kotlin.R
import com.yogandrn.foodmarket.kotlin.model.dummy.HomeVerticalModel
import com.yogandrn.foodmarket.kotlin.model.response.home.Data
import com.yogandrn.foodmarket.kotlin.ui.detail.DetailActivity
import com.yogandrn.foodmarket.kotlin.ui.home.newtaste.HomeNewTasteAdapter
import kotlinx.android.synthetic.main.fragment_home_popular.*

class HomePopularFragment : Fragment(), HomePopularAdapter.ItemAdapterCallback {

    private var popularList : ArrayList<Data>? = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_popular, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        popularList = arguments?.getParcelableArrayList("data")

//        initDataDummy()
        var adapter = HomePopularAdapter(popularList!!, this)
        var layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(activity)
        rcList.layoutManager = layoutManager
        rcList.adapter = adapter

        if (popularList.isNullOrEmpty()) {
            rcList.visibility = View.GONE
            text_empty.visibility = View.VISIBLE
        } else {
            text_empty.visibility = View.GONE
        }
    }

//    fun initDataDummy() {
//        foodList.clear()
//        foodList.add(HomeVerticalModel("Ayam Geprek", 18000, "https://resepkoki.id/wp-content/uploads/2017/11/Resep-Ayam-geprek-jogja-1024x956.jpg", 4.7f))
//        foodList.add(HomeVerticalModel("Nasi Goreng", 15000,"https://poshjournal.com/wp-content/uploads/2019/08/nasi-goreng-recipe-indonesian-fried-rice-7-949x1024.jpg", 4.5f))
////        foodList.add(HomeVerticalModel("Rendang", 25000,"https://factsofindonesia.com/wp-content/uploads/2018/01/rendang.jpg", 5f))
//    }

    override fun onClick(v: View, data: Data) {
        val detail = Intent(context, DetailActivity::class.java)
        detail.putExtra("data", data)
        startActivity(detail)
    }

}
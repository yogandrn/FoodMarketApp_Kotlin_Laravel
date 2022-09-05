package com.yogandrn.foodmarket.kotlin.ui.order.inprogress

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.yogandrn.foodmarket.kotlin.R
import com.yogandrn.foodmarket.kotlin.model.response.transaction.Data
import com.yogandrn.foodmarket.kotlin.ui.orderdetail.OrderDetailActivity
import kotlinx.android.synthetic.main.fragment_in_progress.*

class InProgressFragment : Fragment(), InProgressAdapter.ItemAdapterCallback {

    private var data : ArrayList<Data>? = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_in_progress, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        data = arguments?.getParcelableArrayList("data")

        if (data.isNullOrEmpty()) {
            rcList.visibility = View.GONE
            text_empty.visibility = View.VISIBLE
        } else {
            text_empty.visibility = View.INVISIBLE
            var adapter = InProgressAdapter(data!!, this)
            var layoutManager = LinearLayoutManager(activity)
            rcList.layoutManager = layoutManager
            rcList.adapter = adapter
        }

    }

    override fun onClick(v: View, data: Data) {
        val intent : Intent = Intent(requireActivity(), OrderDetailActivity::class.java)
        intent.putExtra("data", data)
        startActivity(intent)
    }

}
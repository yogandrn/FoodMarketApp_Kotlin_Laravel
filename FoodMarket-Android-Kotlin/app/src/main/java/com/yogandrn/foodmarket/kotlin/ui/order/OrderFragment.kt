package com.yogandrn.foodmarket.kotlin.ui.order

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.yogandrn.foodmarket.kotlin.R
import com.yogandrn.foodmarket.kotlin.model.response.transaction.Data
import kotlinx.android.synthetic.main.fragment_home_popular.*
import kotlinx.android.synthetic.main.fragment_order.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*

class OrderFragment : Fragment(), OrderContract.View {

    private lateinit var presenter : OrderPresenter
    private var progressDialog : Dialog? = null
    private var inProgressList : ArrayList<Data>? = ArrayList()
    private var pastOrderList : ArrayList<Data>? = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val dashboardViewModel =
//            ViewModelProvider(this).get(OrderViewModel::class.java)
//
//        _binding = FragmentOrderBinding.inflate(inflater, container, false)
//        val root: View = binding.root
//
//        val textView: TextView = binding.textDashboard
//        dashboardViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        val root :View = inflater.inflate(R.layout.fragment_order, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = OrderPresenter(this)
        initView()
        presenter.getOrder()

        refresh_order.setOnRefreshListener {
            refresh_order.isRefreshing = true
            initView()
            presenter.getOrder()
            refresh_order.isRefreshing = false
        }

    }

    private fun initView() {
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)
        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(R.color.transparent)
        }

        included1.toolbar.title = resources.getString(R.string.title_orders)
        included1.toolbar.subtitle = resources.getString(R.string.subtitle_order)

    }

    override fun onOrderSuccess(data: List<Data>?) {
        if (data.isNullOrEmpty()) {
            included1.visibility = View.GONE
            layout_order.visibility = View.GONE
            layout_empty.visibility = View.VISIBLE

        } else {
            included1.visibility = View.VISIBLE
            layout_order.visibility = View.VISIBLE
            inProgressList?.clear()
            pastOrderList?.clear()
            for (a in data.indices){
                if (data[a].status.equals("PENDING", true) || data[a].status.equals("ON_PROCESS", true) || data[a].status.equals("ON_DELIVERY", true)) {
                    inProgressList?.add(data[a])
                } else if (data[a].status.equals("SUCCESS", true) || data[a].status.equals("CANCELLED", true) || data[a].status.equals("DELIVERED", true)) {
                    pastOrderList?.add(data[a])
                }
            }
            val sectionPagerAdapter = SectionPagerAdapter(childFragmentManager)
            sectionPagerAdapter.setData(inProgressList, pastOrderList)
            viewPager.adapter = sectionPagerAdapter
            viewPager.currentItem = 0
            tabLayout.setupWithViewPager(viewPager)
        }

    }

    override fun onOrderFailed(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }

}
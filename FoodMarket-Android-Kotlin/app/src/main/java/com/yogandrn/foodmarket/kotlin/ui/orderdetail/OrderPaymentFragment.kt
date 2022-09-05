package com.yogandrn.foodmarket.kotlin.ui.orderdetail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.Navigation
import com.yogandrn.foodmarket.kotlin.R
import com.yogandrn.foodmarket.kotlin.model.response.transaction.Data
import kotlinx.android.synthetic.main.fragment_order_payment.*


class OrderPaymentFragment : Fragment() {

    private var data : Data? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_payment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var bundle : Bundle? = activity?.intent?.extras
        data = bundle?.get("data") as Data
        Log.v("payment", data?.paymentUrl.toString())

        webview_payment.webViewClient = WebViewClient()
        webview_payment.loadUrl(data?.paymentUrl!!)
        button_back.setOnClickListener {
//            (activity as OrderDetailActivity).onBackPressed()
            Navigation.findNavController(it).navigate(R.id.action_order_payment, null)
        }
    }

}
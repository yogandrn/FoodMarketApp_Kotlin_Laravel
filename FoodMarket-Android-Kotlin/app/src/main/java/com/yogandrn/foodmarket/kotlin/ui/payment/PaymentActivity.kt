package com.yogandrn.foodmarket.kotlin.ui.payment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import com.yogandrn.foodmarket.kotlin.R
import com.yogandrn.foodmarket.kotlin.model.response.transaction.Data
import com.yogandrn.foodmarket.kotlin.ui.orderdetail.OrderDetailActivity
import kotlinx.android.synthetic.main.activity_payment.*

class PaymentActivity : AppCompatActivity() {

    private var payment_url : String = ""
    private var data : Data? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

//        var bundle : Bundle = intent?.extras
//        data = bundle?.get("data") as Data
        data = intent?.extras?.get("data") as Data
        payment_url = data?.paymentUrl!!
        Log.v("payment", payment_url)

        webView()

        button_back.setOnClickListener {
            onBackPressed()
//            val i = Intent(applicationContext, OrderDetailActivity::class.java)
//            i.putExtra("data", data)
//            startActivity(i)
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun webView() {
        webview_payment.webViewClient = WebViewClient()
        webview_payment.apply {
            loadUrl(payment_url)
            settings.javaScriptEnabled = true
        }
    }
}
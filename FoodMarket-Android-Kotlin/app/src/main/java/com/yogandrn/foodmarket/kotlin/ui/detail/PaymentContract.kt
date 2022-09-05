package com.yogandrn.foodmarket.kotlin.ui.detail

import android.view.View
import com.yogandrn.foodmarket.kotlin.base.BasePresenter
import com.yogandrn.foodmarket.kotlin.base.BaseView
import com.yogandrn.foodmarket.kotlin.model.response.checkout.CheckoutResponse
import com.yogandrn.foodmarket.kotlin.model.response.transaction.Data

interface PaymentContract {

    interface View : BaseView {
        fun onPaymentSuccess(data : Data, view : android.view.View)
        fun onPaymentFailed(message : String)
    }

    interface Presenter : BasePresenter {
        fun checkout(foodID : Int, userID : Int, quantity : Int, total : Int, view: android.view.View)
    }
}
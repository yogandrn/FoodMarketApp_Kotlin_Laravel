package com.yogandrn.foodmarket.kotlin.ui.orderdetail

import android.view.View
import com.yogandrn.foodmarket.kotlin.base.BasePresenter
import com.yogandrn.foodmarket.kotlin.base.BaseView
import com.yogandrn.foodmarket.kotlin.model.response.checkout.CheckoutResponse
import com.yogandrn.foodmarket.kotlin.model.response.transaction.ConfirmationResponse
import com.yogandrn.foodmarket.kotlin.model.response.transaction.DetailOrderResponse

interface DetailOrderContract {

    interface View : BaseView {
        fun onConfirmSuccess(confirmationResponse: ConfirmationResponse)
        fun onDetailSuccess(detailOrderResponse: DetailOrderResponse)
        fun onConfirmFailed(message : String)
    }

    interface Presenter : BasePresenter {
        fun confirm(orderID : Int)
        fun getDetail(orderID : Int)
    }
}
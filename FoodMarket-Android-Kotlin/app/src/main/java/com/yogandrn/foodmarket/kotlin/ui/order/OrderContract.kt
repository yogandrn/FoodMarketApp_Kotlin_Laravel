package com.yogandrn.foodmarket.kotlin.ui.order

import com.yogandrn.foodmarket.kotlin.base.BasePresenter
import com.yogandrn.foodmarket.kotlin.base.BaseView
import com.yogandrn.foodmarket.kotlin.model.response.transaction.Data

interface OrderContract {

    interface View : BaseView {
        fun onOrderSuccess(data : List<Data>?)
        fun onOrderFailed(message : String)
    }

    interface Presenter : BasePresenter {
        fun getOrder()
    }
}
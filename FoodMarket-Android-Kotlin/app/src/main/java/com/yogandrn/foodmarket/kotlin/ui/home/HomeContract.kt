package com.yogandrn.foodmarket.kotlin.ui.home

import com.yogandrn.foodmarket.kotlin.base.BasePresenter
import com.yogandrn.foodmarket.kotlin.base.BaseView
import com.yogandrn.foodmarket.kotlin.model.response.home.HomeResponse

interface HomeContract {

    interface View : BaseView {
        fun onHomeSuccess(homeResponse : HomeResponse)
        fun onHomeFailed(message : String)
    }

    interface Presenter : BasePresenter {
        fun getHomeItem()
    }
}
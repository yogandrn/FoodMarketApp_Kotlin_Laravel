package com.yogandrn.foodmarket.kotlin.ui.auth

import com.yogandrn.foodmarket.kotlin.base.BasePresenter
import com.yogandrn.foodmarket.kotlin.base.BaseView
import com.yogandrn.foodmarket.kotlin.model.response.login.LoginResponse
import com.yogandrn.foodmarket.kotlin.model.response.login.LogoutResponse

interface LogoutContract {

    interface View : BaseView {
        fun onLogoutSuccess(logoutResponse: LogoutResponse)
        fun onLogoutFailed(message : String)
    }

    interface Presenter : BasePresenter {
        fun logout()
    }
}
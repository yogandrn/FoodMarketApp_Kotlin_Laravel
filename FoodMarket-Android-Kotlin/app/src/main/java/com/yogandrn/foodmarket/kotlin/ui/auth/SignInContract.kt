package com.yogandrn.foodmarket.kotlin.ui.auth

import com.yogandrn.foodmarket.kotlin.base.BasePresenter
import com.yogandrn.foodmarket.kotlin.base.BaseView
import com.yogandrn.foodmarket.kotlin.model.response.login.LoginResponse

interface SignInContract {

    interface View : BaseView {
        fun onLoginSuccess(loginResponse: LoginResponse)
        fun onLoginFailed(message : String)
    }

    interface Presenter : BasePresenter {
        fun submitLogin(email:String, password:String)
    }
}
package com.yogandrn.foodmarket.kotlin.ui.profile

import com.yogandrn.foodmarket.kotlin.base.BasePresenter
import com.yogandrn.foodmarket.kotlin.base.BaseView
import com.yogandrn.foodmarket.kotlin.model.response.login.LoginResponse
import com.yogandrn.foodmarket.kotlin.model.response.login.User

interface ProfileContract {

    interface View : BaseView {
        fun onProfileSuccess(user: User)
        fun onProfileFailed(message : String)
    }

    interface Presenter : BasePresenter {
        fun getUserData()
        fun updateUser(name : String, email : String, phoneNumber : String)
        fun updateAddress(address: String, houseNumber : String, city : String)
    }
}
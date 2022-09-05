package com.yogandrn.foodmarket.kotlin.ui.auth

import android.net.Uri
import com.yogandrn.foodmarket.kotlin.base.BasePresenter
import com.yogandrn.foodmarket.kotlin.base.BaseView
import com.yogandrn.foodmarket.kotlin.model.request.RegisterRequest
import com.yogandrn.foodmarket.kotlin.model.response.login.LoginResponse
import com.yogandrn.foodmarket.kotlin.model.response.login.User

interface SignUpContract {

    interface View : BaseView {
        fun onRegisterSuccess(loginResponse: LoginResponse, view:android.view.View)
        fun onUploadPhotoSuccess(user: User, view:android.view.View)
        fun onRegisterFailed(message:String)
    }

    interface Presenter : BasePresenter {
        fun submitRegister(registerRequest: RegisterRequest, view:android.view.View)
        fun uploadPhotoProfile(filePath: Uri, view:android.view.View)
    }
}
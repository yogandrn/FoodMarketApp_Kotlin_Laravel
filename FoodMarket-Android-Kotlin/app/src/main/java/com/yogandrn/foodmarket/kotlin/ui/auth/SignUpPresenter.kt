package com.yogandrn.foodmarket.kotlin.ui.auth

import android.net.Uri
import android.view.View
import com.yogandrn.foodmarket.kotlin.model.request.RegisterRequest
import com.yogandrn.foodmarket.kotlin.network.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class SignUpPresenter (private val view : SignUpContract.View) : SignUpContract.Presenter{

    private val mCompositeDisposable : CompositeDisposable?

    init {
        this.mCompositeDisposable = CompositeDisposable()
    }

    override fun submitRegister(registerRequest: RegisterRequest, viewParms: View) {
        view.showLoading()
        val disposable = HttpClient.getInstance().getAPI()!!.register(
            registerRequest.name,
            registerRequest.email,
            registerRequest.password,
            registerRequest.password_confirmation,
            registerRequest.phoneNumber,
            registerRequest.address,
            registerRequest.houseNumber,
            registerRequest.city)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()
                    if (it.meta?.status.equals("success", true) ) {
//                        it.data?.let { it -> view.onLoginSuccess(it) }
                        it.data?.let { it1 -> view.onRegisterSuccess(it1, viewParms) }
                    } else {
                        view.onRegisterFailed(it.meta?.message.toString())
                    }
                }
                , {
                    view.dismissLoading()
                    view.onRegisterFailed(it.message.toString())
                }
            )
        mCompositeDisposable!!.add(disposable)

    }

    override fun uploadPhotoProfile(filePath: Uri, viewParms: View) {
        view.showLoading()
        var profileImageFile = File(filePath.path)
        val profileImageRequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), profileImageFile)
        val profileImageParms = MultipartBody.Part.createFormData("file", profileImageFile.name, profileImageRequestBody)
        val disposable = HttpClient.getInstance().getAPI()!!.registerPhoto(profileImageParms)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()
                    if (it.meta?.status.equals("success", true) ) {
                        it.data?.let { it1 -> view.onUploadPhotoSuccess(it1 ,viewParms) }
                    } else {
                        view.onRegisterFailed(it.meta?.message.toString())
                    }
                }
                , {
                    view.dismissLoading()
                    view.onRegisterFailed(it.message.toString())
                }
            )
        mCompositeDisposable!!.add(disposable)

    }

    override fun subscribe() {

    }

    override fun unsubscribe() {
        mCompositeDisposable!!.clear()
    }

}
package com.yogandrn.foodmarket.kotlin.ui.profile

import android.util.Log
import com.yogandrn.foodmarket.kotlin.network.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ProfilePresenter (private val view : ProfileContract.View) : ProfileContract.Presenter{

    private val mCompositeDisposable : CompositeDisposable?

    init {
        this.mCompositeDisposable = CompositeDisposable()
    }

    override fun getUserData() {
        view.showLoading()
        val disposable = HttpClient.getInstance().getAPI()!!.getUserData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()
                    if (it.meta?.status.equals("success", true) ) {
                        it.data?.let { it1 -> view.onProfileSuccess(it1) }
                    } else {
                        it.meta?.message?.let { it -> view.onProfileFailed(it) }
                    }
                }
                , {
                    view.dismissLoading()
                    it.message?.let { it -> view.onProfileFailed(it.toString()) }
//                    view.onLoginFailed(it.message.toString())
                }
            )
        mCompositeDisposable!!.add(disposable)

    }

    override fun updateUser(name : String, email : String, phoneNumber : String)  {
        view.showLoading()
        val disposable = HttpClient.getInstance().getAPI()!!.updateUser(name, email, phoneNumber)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()
                    if (it.meta?.status.equals("success", true) ) {
                        it.data?.let { it1 -> view.onProfileSuccess(it1) }
                    } else {
                        it.meta?.message?.let { it -> view.onProfileFailed(it) }
                    }
                }
                , {
                    view.dismissLoading()
                    it.message?.let { it -> view.onProfileFailed(it.toString()) }
//                    view.onLoginFailed(it.message.toString())
                }
            )
        mCompositeDisposable!!.add(disposable)

    }

    override fun updateAddress(address: String, houseNumber : String, city : String) {
        view.showLoading()
        val disposable = HttpClient.getInstance().getAPI()!!.updateAddress(address, houseNumber, city)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()
                    if (it.meta?.status.equals("success", true) ) {
                        it.data?.let { it1 -> view.onProfileSuccess(it1) }
                    } else {
                        it.meta?.message?.let { it -> view.onProfileFailed(it) }
                    }
                }
                , {
                    view.dismissLoading()
                    it.message?.let { it -> view.onProfileFailed(it.toString()) }
//                    view.onLoginFailed(it.message.toString())
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
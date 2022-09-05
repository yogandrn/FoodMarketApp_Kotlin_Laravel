package com.yogandrn.foodmarket.kotlin.ui.auth

import com.yogandrn.foodmarket.kotlin.network.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LogoutPresenter (private val view : LogoutContract.View) : LogoutContract.Presenter{

    private val mCompositeDisposable : CompositeDisposable?

    init {
        this.mCompositeDisposable = CompositeDisposable()
    }

    override fun logout() {
        view.showLoading()
        val disposable = HttpClient.getInstance().getAPI()!!.logout()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()
                    if (it.meta?.status.equals("success", true) ) {
//                        it.data?.let { it -> view.onLoginSuccess(it) }
                        it?.let { it1 -> view.onLogoutSuccess(it1) }
                    } else {
                        it.meta?.message?.let { it -> view.onLogoutFailed(it) }
                    }
                }
                , {
                    view.dismissLoading()
                    it.message?.let { it -> view.onLogoutFailed(it.toString()) }
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
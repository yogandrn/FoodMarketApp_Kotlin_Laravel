package com.yogandrn.foodmarket.kotlin.ui.home

import com.yogandrn.foodmarket.kotlin.network.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomePresenter (private val view : HomeContract.View) : HomeContract.Presenter{

    private val mCompositeDisposable : CompositeDisposable?

    init {
        this.mCompositeDisposable = CompositeDisposable()
    }

    override fun getHomeItem() {
        view.showLoading()
        val disposable = HttpClient.getInstance().getAPI()!!.homefood()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()
                    if (it.meta?.status.equals("success", true) ) {
                        it.data?.let { it1 -> view.onHomeSuccess(it1) }
                    } else {
                        it.meta?.message?.let { it -> view.onHomeFailed(it) }
                    }
                }
                , {
                    view.dismissLoading()
                    view.onHomeFailed(it.message.toString())
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
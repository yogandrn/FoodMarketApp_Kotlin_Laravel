package com.yogandrn.foodmarket.kotlin.ui.order

import com.yogandrn.foodmarket.kotlin.network.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class OrderPresenter (private val view : OrderContract.View) : OrderContract.Presenter{

    private val mCompositeDisposable : CompositeDisposable?

    init {
        this.mCompositeDisposable = CompositeDisposable()
    }

    override fun getOrder() {
        view.showLoading()
        val disposable = HttpClient.getInstance().getAPI()!!.getTransactions()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()
                    if (it.meta?.status.equals("success", true) ) {
                        it.data?.let { it1 -> view.onOrderSuccess(it1) }
                    } else {
                        it.meta?.message?.let { it -> view.onOrderFailed(it) }
                    }
                }
                , {
                    view.dismissLoading()
                    view.onOrderFailed(it.message.toString())
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
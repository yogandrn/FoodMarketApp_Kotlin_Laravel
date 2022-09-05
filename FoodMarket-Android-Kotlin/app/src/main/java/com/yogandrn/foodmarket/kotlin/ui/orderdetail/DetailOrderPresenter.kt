package com.yogandrn.foodmarket.kotlin.ui.orderdetail

import com.yogandrn.foodmarket.kotlin.network.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailOrderPresenter (private val view : DetailOrderContract.View) : DetailOrderContract.Presenter{

    private val mCompositeDisposable : CompositeDisposable?

    init {
        this.mCompositeDisposable = CompositeDisposable()
    }

    override fun confirm(orderID: Int) {
        view.showLoading()
        val disposable = HttpClient.getInstance().getAPI()!!.confirmOrder(orderID)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()
                    if (it.meta?.status.equals("success", true) ) {
                        it?.let { it1 -> view.onConfirmSuccess(it1) }
                    } else {
                        it.meta?.message?.let { it -> view.onConfirmFailed(it) }
                    }
                }
                , {
                    view.dismissLoading()
                    view.onConfirmFailed(it.message.toString())
                }
            )
        mCompositeDisposable!!.add(disposable)

    }

    override fun getDetail(orderID: Int) {
        view.showLoading()
        val disposable = HttpClient.getInstance().getAPI()!!.detailTransaction(orderID)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()
                    if (it.meta?.status.equals("success", true) ) {
                        it?.let { it1 -> view.onDetailSuccess(it1) }
                    } else {
                        it.meta?.message?.let { it -> view.onConfirmFailed(it) }
                    }
                }
                , {
                    view.dismissLoading()
                    view.onConfirmFailed(it.message.toString())
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
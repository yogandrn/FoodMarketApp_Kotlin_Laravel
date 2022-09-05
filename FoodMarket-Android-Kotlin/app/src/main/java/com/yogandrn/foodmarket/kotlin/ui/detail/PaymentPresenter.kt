package com.yogandrn.foodmarket.kotlin.ui.detail

import android.util.Log
import android.view.View
import com.yogandrn.foodmarket.kotlin.network.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PaymentPresenter (private val view : PaymentContract.View) : PaymentContract.Presenter{

    private val mCompositeDisposable : CompositeDisposable?

    init {
        this.mCompositeDisposable = CompositeDisposable()
    }

    override fun checkout(foodID : Int, userID : Int, quantity : Int, total : Int, viewParams: View) {
        view.showLoading()
        val disposable = HttpClient.getInstance().getAPI()!!.checkout(foodID, userID, quantity, total, "PENDING")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()
                    if (it.meta?.status.equals("success", true) ) {
                        it.data?.let { it1 -> view.onPaymentSuccess(it1, viewParams) }
                    } else {
                        it.meta?.message?.let { it -> view.onPaymentFailed(it) }
                        Log.v("payment", it.meta?.message.toString())
                    }
                }
                , {
                    view.dismissLoading()
                    Log.v("payment", it.message.toString())
                    view.onPaymentFailed(it.message.toString())
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
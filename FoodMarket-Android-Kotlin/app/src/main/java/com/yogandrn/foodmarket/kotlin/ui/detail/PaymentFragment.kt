package com.yogandrn.foodmarket.kotlin.ui.detail

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.yogandrn.foodmarket.kotlin.FoodMarket
import com.yogandrn.foodmarket.kotlin.R
import com.yogandrn.foodmarket.kotlin.model.response.checkout.CheckoutResponse
import com.yogandrn.foodmarket.kotlin.model.response.home.Data
import com.yogandrn.foodmarket.kotlin.model.response.login.User
import com.yogandrn.foodmarket.kotlin.ui.payment.PaymentActivity
import com.yogandrn.foodmarket.kotlin.utils.Helpers
import kotlinx.android.synthetic.main.dialog_checkout.view.*
import kotlinx.android.synthetic.main.fragment_payment.*

class PaymentFragment : Fragment(), PaymentContract.View {

    private var qty : Int = 0
    private var total : Int = 0
    private var progressDialog : Dialog? = null
    private lateinit var presenter: PaymentPresenter
    private var data : Data? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = PaymentPresenter(this)

        data = arguments?.getParcelable<Data>("data")
        qty = arguments?.getInt("qty")!!
        initView(data)

        button_back.setOnClickListener{
            (activity as DetailActivity).onBackPressed()
        }



    }

    fun initView(data: Data?) {
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)
        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(R.color.transparent)
        }



        var user = FoodMarket.getApp().getUser()
        var userResponse = Gson().fromJson(user, User::class.java)

        var tax  = 10 * data?.price!! / 100 * qty
        total = data?.price!! * qty + tax + 10000

        Glide.with(requireActivity()).load(FoodMarket().imageURL + data?.picturePath).into(food_image)
        text_recipient.text = userResponse.name
        text_address.text = userResponse.address + " No. " + userResponse.houseNumber + " - " + userResponse.city
        text_phone_number.text = userResponse.phoneNumber
        food_title.text = data?.name
        food_price.text = Helpers().formatRupiah(data?.price!!)
        food_quantity.text = qty.toString() + " item"
        food_tax.text = Helpers().formatRupiah(tax)
        food_delivery_fee.text = Helpers().formatRupiah(10000)
        food_subtotal.text = Helpers().formatRupiah(data?.price * qty)
        food_total.text = Helpers().formatRupiah(total)

        button_order.setOnClickListener {
            // inflate dialog with customview
            val mDialogView = LayoutInflater.from(requireActivity()).inflate(R.layout.dialog_checkout, null)
            // alert dialog builder
            val mBuilder = AlertDialog.Builder(requireActivity()).setView(mDialogView).setCancelable(true)
            // show alert
            val mAlertDialog = mBuilder.show()
            // set button action
            mDialogView.button_checkout.setOnClickListener {
                presenter.checkout(data.id!!, userResponse.id, qty, total, it)
                mAlertDialog.dismiss()
            }
            mDialogView.button_cancel.setOnClickListener {
                mAlertDialog.dismiss()
            }
        }
    }

    override fun onPaymentSuccess(data: com.yogandrn.foodmarket.kotlin.model.response.transaction.Data, view: View) {
        val i = Intent(requireContext(), PaymentActivity::class.java)

        i.putExtra("data", data)
        startActivity(i)
        activity?.finish()
//        Navigation.findNavController(view).navigate(R.id.action_payment_success)
    }

    override fun onPaymentFailed(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }

}
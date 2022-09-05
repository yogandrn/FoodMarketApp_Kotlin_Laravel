package com.yogandrn.foodmarket.kotlin.ui.orderdetail

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.yogandrn.foodmarket.kotlin.FoodMarket
import com.yogandrn.foodmarket.kotlin.R
import com.yogandrn.foodmarket.kotlin.model.response.transaction.ConfirmationResponse
import com.yogandrn.foodmarket.kotlin.model.response.transaction.Data
import com.yogandrn.foodmarket.kotlin.model.response.transaction.DetailOrderResponse
import com.yogandrn.foodmarket.kotlin.ui.payment.PaymentActivity
import com.yogandrn.foodmarket.kotlin.utils.Helpers
import kotlinx.android.synthetic.main.dialog_confirm.view.*
import kotlinx.android.synthetic.main.fragment_order_detail.*

class OrderDetailFragment : Fragment(), DetailOrderContract.View {

    private lateinit var presenter : DetailOrderPresenter
    private var progressDialog : Dialog? = null
    private var data : Data? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // ambil data argument
        var bundle : Bundle? = activity?.intent?.extras
        data = bundle?.get("data") as Data

        // ambil data detail transaksi
        presenter = DetailOrderPresenter(this)
        presenter.getDetail(data!!.id)

        // setting onrefresh
        refresh_order_detail.setOnRefreshListener {
            onRefresh()
        }

        // jalankan function inisialisasi tampilan
        initView(data!!)

    }

    fun initView(data:Data) {
        //progressdialog loading
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)
        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(R.color.transparent)
        }

        // alert dialog confirm
        button_confirm.setOnClickListener {
            // inflate dialog with customview
            val mDialogView = LayoutInflater.from(requireActivity()).inflate(R.layout.dialog_confirm, null)
            // alert dialog builder
            val mBuilder = AlertDialog.Builder(requireActivity()).setView(mDialogView).setCancelable(true)
            // show alert
            val mAlertDialog = mBuilder.show()
            // set button action
            mDialogView.button_confirm.setOnClickListener {
                mAlertDialog.dismiss()
                presenter.confirm(data.id)
            }
            mDialogView.button_cancel.setOnClickListener {
                mAlertDialog.dismiss()
            }

        }

        // tampilkan button sesuai dengan status order
        when (data.status) {
            "ON_DELIVERY" -> button_confirm.visibility = View.VISIBLE
            "PENDING" -> button_bayar.visibility = View.VISIBLE
            else -> button_back.visibility = View.VISIBLE
        }

        // menampilkan status pesanan
        var status : String = ""
        when (data.status) {
            "PENDING" -> status = resources.getString(R.string.pending)
            "ON_PROCESS" -> status = resources.getString(R.string.on_process)
            "ON_DELIVERY" -> status = resources.getString(R.string.on_delivery)
            "SUCCESS" -> status = resources.getString(R.string.success)
            "CANCELLED" -> status = resources.getString(R.string.cancelled)
        }

        // menampilkan data pesanan
        detail_date.text = data.createdAt
        detail_status.text = status
        if (data.status == "CANCELLED") {
            detail_status.setTextColor(resources.getColor(R.color.red))
        }
        detail_code.text = data.code
        text_recipient.text = data.user.name
        text_phone_number.text = data.user.phoneNumber
        text_address.text = data.user.address + " No. " + data.user.houseNumber + " - " + data.user.city
        Glide.with(requireActivity()).load(FoodMarket().imageURL + data.food.picturePath).into(detail_food_image)
        detail_food_title.text = data.food.name
        detail_food_price.text = Helpers().formatRupiah(data.food.price)
        detail_food_quantity.text = data.quantity.toString() + " items"
        detail_delivery_fee.text = Helpers().formatRupiah(10000)
        detail_subtotal.text = Helpers().formatRupiah(data.food.price * data.quantity)
        detail_food_price.text = Helpers().formatRupiah(data.food.price)
        detail_tax.text = Helpers().formatRupiah(data.food.price.div(10))
        detail_total.text = Helpers().formatRupiah(data.total)

        // menuju halaman pembayaran
        button_bayar.setOnClickListener {
            val i = Intent(requireContext(), PaymentActivity::class.java)
            i.putExtra("data", data)
            startActivity(i)
        }

        // kembali
        button_back.setOnClickListener {
            (activity as OrderDetailActivity).onBackPressed()
        }
    }

    // function ketika refresh
    fun onRefresh() {
        refresh_order_detail.isRefreshing = true
        presenter.getDetail(data!!.id)
        refresh_order_detail.isRefreshing =  false
    }

    override fun onConfirmSuccess(confirmationResponse: ConfirmationResponse) {
        Toast.makeText(requireContext(), resources.getString(R.string.order_confirmed), Toast.LENGTH_SHORT).show()
        onRefresh()
    }

    override fun onDetailSuccess(detailOrderResponse: DetailOrderResponse) {
//        var data : Data = detailOrderResponse.data!!
        initView(detailOrderResponse.data!!)
    }

    override fun onConfirmFailed(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        button_confirm.visibility = View.GONE
        button_back.visibility = View.GONE
        button_bayar.visibility = View.GONE
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }

}
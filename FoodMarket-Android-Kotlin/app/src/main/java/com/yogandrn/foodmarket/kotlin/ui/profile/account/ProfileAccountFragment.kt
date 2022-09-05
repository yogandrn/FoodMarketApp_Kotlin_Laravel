package com.yogandrn.foodmarket.kotlin.ui.profile.account

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.gson.Gson
import com.yogandrn.foodmarket.kotlin.FoodMarket
import com.yogandrn.foodmarket.kotlin.R
import com.yogandrn.foodmarket.kotlin.model.dummy.ProfileMenuModel
import com.yogandrn.foodmarket.kotlin.model.response.login.LogoutResponse
import com.yogandrn.foodmarket.kotlin.model.response.login.User
import com.yogandrn.foodmarket.kotlin.ui.MainActivity
import com.yogandrn.foodmarket.kotlin.ui.auth.AuthActivity
import com.yogandrn.foodmarket.kotlin.ui.auth.LogoutContract
import com.yogandrn.foodmarket.kotlin.ui.auth.LogoutPresenter
import com.yogandrn.foodmarket.kotlin.ui.profile.ProfileMenuAdapter
import com.yogandrn.foodmarket.kotlin.ui.profile.editaddress.EditAddressActivity
import com.yogandrn.foodmarket.kotlin.ui.profile.editprofile.EditProfileActivity
import kotlinx.android.synthetic.main.dialog_logout.view.*
import kotlinx.android.synthetic.main.fragment_profile_account.*

class ProfileAccountFragment : Fragment(), ProfileMenuAdapter.ItemAdapterCallback, LogoutContract.View{

    private var menu : ArrayList<ProfileMenuModel> = ArrayList()
    private  lateinit var  presenter: LogoutPresenter
    private var progressDialog : Dialog? = null
    private var logoutDialog : Dialog? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_account, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = LogoutPresenter(this)

//        initDataDummy()
//        var adapter = ProfileMenuAdapter(menu, this)
//        var layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(activity)
//        rcList.adapter = adapter
//        rcList.layoutManager = layoutManager

        button_edit_profile.setOnClickListener {
            var user = FoodMarket.getApp().getUser()
            var userResponse = Gson().fromJson(user, User::class.java)
            var intent = Intent(requireActivity(), EditProfileActivity::class.java)
            intent.putExtra("data", userResponse)
            startActivity(intent)
        }
        button_home_address.setOnClickListener {
            var user = FoodMarket.getApp().getUser()
            var userResponse = Gson().fromJson(user, User::class.java)
            var intent = Intent(requireActivity(), EditAddressActivity::class.java)
            intent.putExtra("data", userResponse)
            startActivity(intent)
        }

        button_logout.setOnClickListener {
            // inflate dialog with customview
            val mDialogView = LayoutInflater.from(requireActivity()).inflate(R.layout.dialog_logout, null)
            // alert dialog builder
            val mBuilder = AlertDialog.Builder(requireActivity()).setView(mDialogView).setCancelable(true)
            // show alert
            val mAlertDialog = mBuilder.show()
            // set button action
            mDialogView.button_logout.setOnClickListener {
                mAlertDialog.dismiss()
                presenter.logout()
            }
            mDialogView.button_cancel.setOnClickListener {
                mAlertDialog.dismiss()
            }
        }
    }

    fun initDataDummy() {
        menu.clear()
        menu.add(ProfileMenuModel("Edit Profile"))
        menu.add(ProfileMenuModel("Home Address"))
        menu.add(ProfileMenuModel("Security"))
        menu.add(ProfileMenuModel("Payments"))
    }

    fun initView() {
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)
        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(R.color.transparent)
        }
//        logoutDialog = Dialog(requireContext())
//        val logoutDialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)
//        logoutDialog?.let {
//            it.setContentView(logoutDialogLayout)
//            it.setCancelable(true)
//            it.window?.setBackgroundDrawableResource(R.color.transparent)
//            logoutDialogLayout.button_cancel.setOnClickListener {
//                logoutDialog?.dismiss()
//            }
//            logoutDialogLayout.button_logout.setOnClickListener {
//                logoutDialog?.dismiss()
//                presenter.logout()
//            }
//        }
    }

    override fun onClick(v: View, data: ProfileMenuModel) {
        Toast.makeText(context, data.title + " is clicked!", Toast.LENGTH_SHORT).show()
    }

    override fun onLogoutSuccess(logoutResponse: LogoutResponse) {
        Toast.makeText(requireActivity(), resources.getString(R.string.logout_success), Toast.LENGTH_SHORT).show()
        FoodMarket.getApp().setUser("")
        FoodMarket.getApp().setToken("")
        FoodMarket.getApp().deleteSession()
        val home = Intent(activity, AuthActivity::class.java)
        startActivity(home)
        activity?.finishAffinity()
    }

    override fun onLogoutFailed(message: String) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }
}
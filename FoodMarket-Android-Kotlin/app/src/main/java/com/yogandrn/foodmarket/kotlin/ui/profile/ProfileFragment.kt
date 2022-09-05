package com.yogandrn.foodmarket.kotlin.ui.profile

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import com.yogandrn.foodmarket.kotlin.FoodMarket
import com.yogandrn.foodmarket.kotlin.R
import com.yogandrn.foodmarket.kotlin.model.response.login.LoginResponse
import com.yogandrn.foodmarket.kotlin.model.response.login.LogoutResponse
import com.yogandrn.foodmarket.kotlin.model.response.login.User
import com.yogandrn.foodmarket.kotlin.ui.auth.LogoutContract
import com.yogandrn.foodmarket.kotlin.ui.auth.LogoutPresenter
import com.yogandrn.foodmarket.kotlin.ui.auth.SignUpPresenter
import com.yogandrn.foodmarket.kotlin.ui.home.HomePresenter
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment(), ProfileContract.View{

    private lateinit var presenter : ProfilePresenter
    var progressDialog : Dialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val notificationsViewModel =
//            ViewModelProvider(this).get(ProfileViewModel::class.java)
//
//        _binding = FragmentProfileBinding.inflate(inflater, container, false)
//        val root: View = binding.root
//
//        val textView: TextView = binding.textNotifications
//        notificationsViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        val root :View = inflater.inflate(R.layout.fragment_profile, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = ProfilePresenter(this)
        presenter.getUserData()
        initView()


        refresh_profile.setOnRefreshListener {
            refresh_profile.isRefreshing = true
            presenter.getUserData()
            refresh_profile.isRefreshing = false
        }

    }

    fun initView () {

        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)
        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(R.color.transparent)
        }

        val sectionPagerAdapter = SectionPagerAdapter(childFragmentManager)
        viewPager.adapter = sectionPagerAdapter
        viewPager.currentItem = 0
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onProfileSuccess(user: User) {
        val gson = Gson()
        val json = gson.toJson(user)
        FoodMarket.getApp().setUser(json)
        var user = FoodMarket.getApp().getUser()
        var userResponse = Gson().fromJson(user, User::class.java)

        Glide.with(requireActivity())
            .load(FoodMarket().imageURL + userResponse.profile_photo_path)
            .apply(RequestOptions.circleCropTransform())
            .into(profile_photo)
        profile_name.text = userResponse.name
        profile_email.text = userResponse.email
    }

    override fun onProfileFailed(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }

}
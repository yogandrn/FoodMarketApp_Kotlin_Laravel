package com.yogandrn.foodmarket.kotlin.ui.auth

import android.app.Activity
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
import com.bumptech.glide.request.RequestOptions
import com.github.dhaval2404.imagepicker.ImagePicker
import com.yogandrn.foodmarket.kotlin.R
import com.yogandrn.foodmarket.kotlin.model.request.RegisterRequest
import kotlinx.android.synthetic.main.fragment_signup.*

class SignupFragment : Fragment() {

    var filePath : Uri ?= null
    val REQUEST_CODE : Int = 100

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        iniListener()

//        signup_photo.setOnClickListener{
//            val intent = Intent(Intent.ACTION_PICK)
//            intent.type = "image/*"
//            startActivityForResult(intent, REQUEST_CODE)
//        }

        signup_photo.setOnClickListener {
            ImagePicker.with(this)
                .galleryOnly()
                .cropSquare()
                .maxResultSize(720, 720)
                .start()
        }

        signup_continue.setOnClickListener{
            var name =signup_name.text.toString()
            var email =signup_email.text.toString()
            var password =signup_password.text.toString()
            var confpass =signup_confirm_password.text.toString()
            if (name.isNullOrEmpty()) {
                signup_name.setError(resources.getString(R.string.name_empty))
                signup_name.requestFocus()
            } else if (email.isNullOrEmpty()) {
                signup_email.setError(resources.getString(R.string.email_empty))
                signup_email.requestFocus()
            } else if (!email.contains("@") || !email.contains(".")) {
                signup_email.setError(resources.getString(R.string.invalid_email))
                signup_email.requestFocus()
            } else if (password.isNullOrEmpty()) {
                signup_password.setError(resources.getString(R.string.password_empty))
                signup_password.requestFocus()
            } else if (password.length < 8) {
                signup_password.setError(resources.getString(R.string.password_min8))
                signup_password.requestFocus()
            } else if (confpass.isNullOrEmpty()) {
                signup_confirm_password.setError(resources.getString(R.string.confpass_empty))
                signup_confirm_password.requestFocus()
            } else if (!confpass.equals(password)) {
                signup_confirm_password.setError(resources.getString(R.string.confpass_unmatched))
                signup_confirm_password.requestFocus()
            } else {
                var data = RegisterRequest(name, email, password, confpass, "", "", "", "", filePath)

                var bundle = Bundle()
                bundle.putParcelable("data", data)

                Navigation.findNavController(it).navigate(R.id.action_signup_address, bundle)
                (activity as AuthActivity).toolbarSignupAddress()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            filePath = data?.data

            Log.v("path", filePath.toString())

            Glide.with(this)
                .load(filePath)
                .apply(RequestOptions.circleCropTransform())
                .into(signup_photo)
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(context, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }

    private fun iniListener() {
        signup_photo.setOnClickListener {
            ImagePicker.with(this)
                .cameraOnly()
                .start()
        }
    }

}
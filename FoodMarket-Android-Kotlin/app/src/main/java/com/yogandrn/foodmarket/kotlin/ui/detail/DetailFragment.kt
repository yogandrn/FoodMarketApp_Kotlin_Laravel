package com.yogandrn.foodmarket.kotlin.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.yogandrn.foodmarket.kotlin.FoodMarket
import com.yogandrn.foodmarket.kotlin.R
import com.yogandrn.foodmarket.kotlin.model.response.home.Data
import com.yogandrn.foodmarket.kotlin.utils.Helpers
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    var qty : Int = 1
    var newbundle : Bundle? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var bundle : Bundle? = activity?.intent?.extras
        var data : Data = bundle?.get("data") as Data

        initView(data)


//        arguments?.let {
//            DetailFragmentArgf.fromBundle(it).data.let{
//                initView(it)
//            }
//        }

        button_decrement.setOnClickListener {
            if (qty == 1) {
//                qty == 1
//                text_quantity.text = 1
//                detail_total.text = Helpers().formatRupiah()
            } else {
                qty--
                text_quantity.text = qty.toString()
                detail_total.text = Helpers().formatRupiah(data.price!! * qty)
            }
        }

        button_increment.setOnClickListener {
            qty++
            text_quantity.text = qty.toString()
            detail_total.text = Helpers().formatRupiah(data.price!! * qty)
            Log.v("qty", qty.toString())
        }

        button_back.setOnClickListener{
            (activity as DetailActivity).onBackPressed()
        }
        button_order.setOnClickListener {
            newbundle?.putInt("qty", qty)
            detail_total.text = Helpers().formatRupiah(data?.price!!)
            Navigation.findNavController(it).navigate(R.id.action_payment, newbundle)

        }
    }
//
    fun initView(data : Data?) {
    newbundle = bundleOf("data" to data)
        Glide.with(requireActivity()).load(FoodMarket().imageURL + data?.picturePath).into(detail_image)
        detail_title.text = data?.name
        detail_price.text = Helpers().formatRupiah(data?.price!!)
        detail_description.text = data?.description
        detail_ingredient.text = data?.ingredients
        detail_rate.rating = data?.rate?.toFloat() ?: 0f
        detail_rating.text = data?.rate.toString()
        detail_total.text = Helpers().formatRupiah(data?.price!! * qty)


    }

}
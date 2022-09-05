package com.yogandrn.foodmarket.kotlin.model.response.checkout

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.yogandrn.foodmarket.kotlin.model.response.login.User
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CheckoutResponse(
    @Expose
    @SerializedName("created_at")
    val createdAt: String,
    @Expose
    @SerializedName("deleted_at")
    val deletedAt: String?,
    @Expose
    @SerializedName("food")
    val food: Food,
    @Expose
    @SerializedName("food_id")
    val foodId: Int,
    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("payment_url")
    val paymentUrl: String,
    @Expose
    @SerializedName("quantity")
    val quantity: Int,
    @Expose
    @SerializedName("status")
    val status: String,
    @Expose
    @SerializedName("total")
    val total: Int,
    @Expose
    @SerializedName("updated_at")
    val updatedAt: String,
    @Expose
    @SerializedName("user")
    val user: User,
    @Expose
    @SerializedName("user_id")
    val userId: Int
) : Parcelable

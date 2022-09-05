package com.yogandrn.foodmarket.kotlin.model.response.login

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.yogandrn.foodmarket.kotlin.model.response.Meta

data class LogoutResponse(
    @Expose
    @SerializedName("meta")
    val meta: Meta,
    @Expose
    @SerializedName("data")
    val data: Boolean,
)

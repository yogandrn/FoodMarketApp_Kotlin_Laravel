package com.yogandrn.foodmarket.kotlin.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Response<T>(
    @Expose
    @SerializedName("data")
    val data: T?= null,
    @Expose
    @SerializedName("meta")
    val meta: Meta ? = null
)


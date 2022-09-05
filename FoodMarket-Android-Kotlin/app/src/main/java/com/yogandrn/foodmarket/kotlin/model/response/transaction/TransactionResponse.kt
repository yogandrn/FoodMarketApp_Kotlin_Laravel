package com.yogandrn.foodmarket.kotlin.model.response.transaction

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.yogandrn.foodmarket.kotlin.model.response.Meta

data class TransactionResponse(
    @Expose
    @SerializedName("meta")
    var meta : Meta ,
    @Expose
    @SerializedName("data")
    var data: List<Data>? = null,
)

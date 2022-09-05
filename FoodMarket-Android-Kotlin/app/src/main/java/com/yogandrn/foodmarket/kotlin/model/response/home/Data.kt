package com.yogandrn.foodmarket.kotlin.model.response.home

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data(
    @Expose
    @SerializedName("created_at")
    val createdAt: Long?,
    @Expose
    @SerializedName("deleted_at")
    val deletedAt: String?,
    @Expose
    @SerializedName("description")
    val description: String?,
    @Expose
    @SerializedName("id")
    val id: Int?,
    @Expose
    @SerializedName("ingredient")
    val ingredients: String?,
    @Expose
    @SerializedName("name")
    val name: String?,
    @Expose
    @SerializedName("picturePath")
    val picturePath: String?,
    @Expose
    @SerializedName("price")
    val price: Int?,
    @Expose
    @SerializedName("rate")
    var rate : Double?,
    @Expose
    @SerializedName("type")
    val types: String?,
    @Expose
    @SerializedName("updated_at")
    val updatedAt: Long?
) : Parcelable
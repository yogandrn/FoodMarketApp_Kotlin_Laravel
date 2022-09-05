package com.yogandrn.foodmarket.kotlin.model.response.transaction

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    @Expose
    @SerializedName("address")
    var address: String,
    @Expose
    @SerializedName("city")
    var city: String,
    @Expose
    @SerializedName("created_at")
    var createdAt: Long,
    @Expose
    @SerializedName("current_team_id")
    var currentTeamId: String?,
    @Expose
    @SerializedName("email")
    var email: String,
    @Expose
    @SerializedName("email_verified_at")
    var emailVerifiedAt: String?,
    @Expose
    @SerializedName("houseNumber")
    var houseNumber: String,
    @Expose
    @SerializedName("id")
    var id: Int,
    @Expose
    @SerializedName("name")
    var name: String,
    @Expose
    @SerializedName("phoneNumber")
    var phoneNumber: String,
    @Expose
    @SerializedName("profile_photo_path")
    var profilePhotoPath: String?,
    @Expose
    @SerializedName("profile_photo_url")
    var profilePhotoUrl: String?,
    @Expose
    @SerializedName("roles")
    var roles: String,
    @Expose
    @SerializedName("updated_at")
    var updatedAt: Long?
) : Parcelable

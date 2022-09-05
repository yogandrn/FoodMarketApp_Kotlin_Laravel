package com.yogandrn.foodmarket.kotlin.network

import com.yogandrn.foodmarket.kotlin.model.response.Response
import com.yogandrn.foodmarket.kotlin.model.response.checkout.CheckoutResponse
import com.yogandrn.foodmarket.kotlin.model.response.home.HomeResponse
import com.yogandrn.foodmarket.kotlin.model.response.login.LoginResponse
import com.yogandrn.foodmarket.kotlin.model.response.login.LogoutResponse
import com.yogandrn.foodmarket.kotlin.model.response.login.User
import com.yogandrn.foodmarket.kotlin.model.response.transaction.ConfirmationResponse
import com.yogandrn.foodmarket.kotlin.model.response.transaction.Data
import com.yogandrn.foodmarket.kotlin.model.response.transaction.DetailOrderResponse
import com.yogandrn.foodmarket.kotlin.model.response.transaction.TransactionResponse
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.*

interface EndPoint {

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email : String,
        @Field("password") password : String,
    ) : Observable<Response<LoginResponse>>

    @GET("user")
    fun getUserData() : Observable<Response<User>>

    @FormUrlEncoded
    @POST("user/update")
    fun updateUser(
        @Field("name") name : String,
        @Field("email") email : String,
        @Field("phoneNumber") phoneNumber : String,
    ) : Observable<Response<User>>

    @FormUrlEncoded
    @POST("user/update")
    fun updateAddress(
        @Field("address") address : String,
        @Field("houseNumber") houseNumber : String,
        @Field("city") city : String,
    ) : Observable<Response<User>>

    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("name") name : String,
        @Field("email") email : String,
        @Field("password") password : String,
        @Field("password_confirmation") password_confirmation : String,
        @Field("phoneNumber") phone_number : String,
        @Field("address") address : String,
        @Field("houseNumber") house_number : String,
        @Field("city") city : String,
    ) : Observable<Response<LoginResponse>>

    @POST("logout")
    fun logout() : Observable<LogoutResponse>

    @Multipart
    @POST("user/photo")
    fun registerPhoto(
        @Part profileImage:MultipartBody.Part
    ) : Observable<Response<User>>

    @GET("food")
    fun homefood() : Observable<Response<HomeResponse>>

    @FormUrlEncoded
    @POST("checkout")
    fun checkout(
        @Field("food_id") foodID : Int,
        @Field("user_id") userID : Int,
        @Field("quantity") quantity : Int,
        @Field("total") total : Int,
        @Field("status") status : String,
    ) : Observable<Response<Data>>

    @GET("transactions")
    fun getTransactions() : Observable<TransactionResponse>

    @GET("transaction/{id}")
    fun detailTransaction(@Path("id") orderID : Int) : Observable<DetailOrderResponse>

    @POST("transaction/{id}/confirm")
    fun confirmOrder(@Path("id") orderID : Int) :Observable<ConfirmationResponse>
}
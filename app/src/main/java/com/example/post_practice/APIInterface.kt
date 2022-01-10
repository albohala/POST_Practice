package com.example.post_practice

import retrofit2.Call
import retrofit2.http.*

interface APIInterface {
    @GET("test/")
    fun getUsers(): Call<Users>

    @POST("test/")
    fun postUsers(@Body userData: UsersItem): Call<UsersItem>

    @PUT("/test/{id}")
    fun updateUser(@Path("id") id: Int, @Body userData: UsersItem): Call<UsersItem>

    @DELETE("/test/{id}")
    fun deleteUser(@Path("id") id: Int): Call<Void>
}
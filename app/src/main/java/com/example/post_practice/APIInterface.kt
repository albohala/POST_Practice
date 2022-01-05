package com.example.post_practice

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIInterface {
    @GET("test/")
    fun getUsers(): Call<Users>

    @POST("test/")
    fun postUsers(@Body userData: UsersItem): Call<UsersItem>
}
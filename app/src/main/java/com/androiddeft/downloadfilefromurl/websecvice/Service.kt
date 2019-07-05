package com.example.bookhotels.websecvice

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface Service {

    @Streaming
    @GET("download/")
    fun downloadFile(@Query("video") video: String): Call<ResponseBody>

}
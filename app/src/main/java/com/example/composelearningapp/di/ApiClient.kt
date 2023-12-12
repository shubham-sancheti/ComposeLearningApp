package com.example.composelearningapp.di

import kotlinx.coroutines.flow.flow
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
     sealed class  ApiResponse<out T>(loading: loading) {
        object loading : ApiResponse<Nothing>(loading)
         class  SUCCESS <R>(val reponse:R) : ApiResponse<R>(loading)
         class ERROR(val error :String): ApiResponse<Nothing>(loading)
    }
    val apiCall: ApiInterface by lazy {
        val retrofit = Retrofit.Builder().baseUrl("https://api.jsonbin.io/").addConverterFactory(GsonConverterFactory.create()).build()
        val apiCall = retrofit.create(ApiInterface::class.java);
        apiCall
    }


    fun<T> getResponse(call :suspend ()->Response<T> )= flow<ApiResponse<T>> {
        emit(ApiResponse.loading)
        val response = call()
        if(response.isSuccessful && response.body() != null){
            emit(ApiResponse.SUCCESS(response.body()!!))
        }else{
            emit(ApiResponse.ERROR("Something went wrong."))
        }

    }
}
package com.example.composelearningapp.di

class NetworkRepository {

    fun getCategories() = ApiClient.getResponse {
        ApiClient.apiCall.getCategories()
    }

    fun getTweets(category:String) = ApiClient.getResponse {
        ApiClient.apiCall.getTweets(category)
    }
}
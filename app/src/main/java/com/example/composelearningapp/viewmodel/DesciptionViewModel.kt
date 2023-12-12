package com.example.composelearningapp.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composelearningapp.di.ApiClient
import com.example.composelearningapp.di.NetworkRepository
import com.example.composelearningapp.models.TweetListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DesciptionViewModel @Inject constructor(val networkRepo:NetworkRepository, val savedStateHandle: SavedStateHandle):ViewModel() {
    private var listResponse = mutableStateOf<ApiClient.ApiResponse<List<TweetListItem>>> ( ApiClient.ApiResponse.loading)
    val listState:State<ApiClient.ApiResponse<List<TweetListItem>>> = listResponse

    init {
        viewModelScope.launch {
            val category = savedStateHandle.get<String>("category")?:"android"
            networkRepo.getTweets("tweets[?(@.category==\"$category\")]").collect{
                listResponse.value = it
            }
        }
    }

/*
    fun getDescriptions(categories:String) = networkRepo.getTweets(categories)
*/
}
package com.example.composelearningapp.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composelearningapp.di.ApiClient
import com.example.composelearningapp.di.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val networkRepo:NetworkRepository):ViewModel() {
    val mstate  = mutableStateOf<ApiClient.ApiResponse<List<String>>>(ApiClient.ApiResponse.loading)
    val state :State<ApiClient.ApiResponse<List<String>>> = mstate
    fun loadCategories(){
        viewModelScope.launch {
            networkRepo.getCategories().collect{
                mstate.value = it
            }
        }
    }

}
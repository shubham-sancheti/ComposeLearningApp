package com.example.composelearningapp.hilt

import com.example.composelearningapp.di.NetworkRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class HiltSingelton {
    @Singleton
    @Provides
    fun getNetworkRepo() = NetworkRepository()
}
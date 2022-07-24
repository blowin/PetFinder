package com.lefarmico.petfinder.di

import com.lefarmico.petfinder.data.repository.SearchPostRepositoryTestImpl
import com.lefarmico.petfinder.domain.repository.SearchPostRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun getSearchPostRepo(searchPostRepositoryImpl: SearchPostRepositoryTestImpl): SearchPostRepository
}

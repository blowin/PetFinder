package com.lefarmico.petfinder.di

import com.lefarmico.proto.PetServiceGrpcGrpc
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    @Singleton
    fun provideManagedChannel(): ManagedChannel =
        ManagedChannelBuilder
            .forAddress("localhost", 80)
            .build()

    @Provides
    @Singleton
    fun provideBlockingStub(
        managedChannel: ManagedChannel
    ): PetServiceGrpcGrpc.PetServiceGrpcBlockingStub =
        PetServiceGrpcGrpc.newBlockingStub(managedChannel)
}

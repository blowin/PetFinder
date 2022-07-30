package com.lefarmico.petfinder.di

import android.content.Context
import com.lefarmico.proto.PetServiceGrpcGrpc
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.grpc.ManagedChannel
import io.grpc.android.AndroidChannelBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    @Singleton
    fun provideManagedChannel(
        @ApplicationContext context: Context
    ): ManagedChannel =
        AndroidChannelBuilder
            .forAddress("10.0.2.2", 80)
            .context(context)
            .usePlaintext()
            .executor(Dispatchers.IO.asExecutor())
            .build()

    @Provides
    @Singleton
    fun provideBlockingStub(
        managedChannel: ManagedChannel
    ): PetServiceGrpcGrpc.PetServiceGrpcBlockingStub =
        PetServiceGrpcGrpc.newBlockingStub(managedChannel)
}

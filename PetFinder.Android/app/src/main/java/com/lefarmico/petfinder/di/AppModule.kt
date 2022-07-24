package com.lefarmico.petfinder.di

import com.lefarmico.core.base.app.ApplicationConfigInitializer
import com.lefarmico.core.base.app.config.TimberConfig
import com.lefarmico.petfinder.App
import com.lefarmico.petfinder.testData.TestDataSource
import com.lefarmico.petfinder.utils.AppConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideApp(): App {
        return App()
    }

    @Provides
    @Singleton
    fun appConfig(): AppConfig {
        return AppConfig()
    }

    @Provides
    @Singleton
    fun timberConfig(
        appConfig: AppConfig
    ): TimberConfig {
        return TimberConfig(
            appConfig.isDebug()
        )
    }

    @Provides
    @Singleton
    fun provideAppConfigInitializer(
        timberConfig: TimberConfig
    ): ApplicationConfigInitializer {
        return ApplicationConfigInitializer(timberConfig)
    }

    @Provides
    @Singleton
    fun provideTestData(): TestDataSource = TestDataSource()
}

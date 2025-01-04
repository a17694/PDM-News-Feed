package com.example.news

import android.content.Context
import com.example.news.service.NewsApi
import com.example.news.service.ObservadorApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext app: Context): Context {
        return app
    }

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return NewsApi // `object` pode ser retornado diretamente
    }

    @Provides
    @Singleton
    fun provideObservadorApi(): ObservadorApi {
        return ObservadorApi // `object` pode ser retornado diretamente
    }
}

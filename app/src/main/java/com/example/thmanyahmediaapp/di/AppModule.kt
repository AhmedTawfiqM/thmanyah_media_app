package com.example.thmanyahmediaapp.di

import com.example.thmanyahmediaapp.presentation.theme.ThemeManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideThemeManager(): ThemeManager {
        return ThemeManager()
    }
}

package com.proyect.worldflags.di

import com.proyect.worldflags.data.repository.CountryRepositoryImpl
import com.proyect.worldflags.domain.repository.CountryRepository
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
    abstract fun bindCountryRepository(
        repositoryImpl: CountryRepositoryImpl
    ): CountryRepository
}

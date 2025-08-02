package com.droidnext.countrysearch.di

import com.droidnext.countrysearch.data.repository.SearchRepository
import com.droidnext.countrysearch.data.service.SearchService
import com.droidnext.countrysearch.domain.repository.ISearchRepository
import com.droidnext.countrysearch.domain.usecases.SearchUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    const val BASE_URL = "https://api.thecompaniesapi.com/v2/"

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideService(
        retrofit: Retrofit
    ): SearchService = retrofit.create<SearchService>(SearchService::class.java)

    @Singleton
    @Provides
    fun providesRepo(
        service: SearchService
    ): ISearchRepository = SearchRepository(service)

    @Provides
    fun providesUseCase(
        repo: ISearchRepository
    ) = SearchUseCase(repo)
}
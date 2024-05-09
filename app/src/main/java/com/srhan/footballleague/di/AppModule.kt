package com.srhan.footballleague.di

import android.app.Application
import androidx.room.Room
import com.srhan.footballleague.BuildConfig
import com.srhan.footballleague.data.local.CompetitionDatabase
import com.srhan.footballleague.data.remote.CompetitionApiService
import com.srhan.footballleague.data.remote.CustomHeaderInterceptor
import com.srhan.footballleague.data.repository.CacheCompetitionRepositoryImpl
import com.srhan.footballleague.data.repository.CompetitionsNetworkRepositoryImpl
import com.srhan.footballleague.domain.repository.CacheCompetitionRepository
import com.srhan.footballleague.domain.repository.CompetitionsNetworkRepository
import com.srhan.footballleague.domain.usecase.CacheCompetitionUseCade
import com.srhan.footballleague.domain.usecase.GetCompetitionFromLocalUseCase
import com.srhan.footballleague.domain.usecase.GetCompetitionFromNetworkUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }
//    @Provides
//    @Singleton
//    fun provideCertificatePinner(): CertificatePinner {
//        return CertificatePinner.Builder()
//            .add("domain.com", "sha256/AAAAA")
//            .build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor, certificatePinner: CertificatePinner): OkHttpClient {
//        return OkHttpClient.Builder()
//            .addInterceptor(httpLoggingInterceptor)
//            .certificatePinner(certificatePinner)
//            .build()
//    }

    @Provides
    @Singleton
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(CustomHeaderInterceptor())
            .build()
    }


    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): CompetitionApiService =
        retrofit.create(CompetitionApiService::class.java)

    @Provides
    @Singleton
    fun provideDatabase(app: Application): CompetitionDatabase =
        Room.databaseBuilder(app, CompetitionDatabase::class.java, "Competition_database")
            .build()


    @Provides
    @Singleton
    fun provideCompetitionRepo(
        competitionApiService: CompetitionApiService, competitionDatabase: CompetitionDatabase
    ): CompetitionsNetworkRepository =
        CompetitionsNetworkRepositoryImpl(competitionApiService, competitionDatabase)


    @Singleton
    @Provides
    fun provideGetCompetitionUseCase(competitionsNetworkRepository: CompetitionsNetworkRepository) =
        GetCompetitionFromNetworkUseCase(competitionsNetworkRepository)

    @Provides
    @Singleton
    fun provideCacheCompetitionRepo(competitionDatabase: CompetitionDatabase): CacheCompetitionRepository =
        CacheCompetitionRepositoryImpl(competitionDatabase)


    @Singleton
    @Provides
    fun provideCacheCompetitionUseCase(cacheCompetitionRepository: CacheCompetitionRepository) =
        CacheCompetitionUseCade(cacheCompetitionRepository)

    @Singleton
    @Provides
    fun provideGetCompetitionFromLocalUseCase(cacheCompetitionRepository: CacheCompetitionRepository) =
        GetCompetitionFromLocalUseCase(cacheCompetitionRepository)

}
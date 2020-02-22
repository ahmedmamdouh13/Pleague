package com.ahmedmamdouh13.theleague.ui.di.module

import android.content.Context
import androidx.room.Room
import com.ahmedmamdouh13.theleague.data.local.LeagueDatabase
import com.ahmedmamdouh13.theleague.data.local.MatchesDao
import com.ahmedmamdouh13.theleague.data.remote.LeagueService
import com.ahmedmamdouh13.theleague.ui.di.scope.AppScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class DataModule(context: Context) {
    val ctx = context
    @Provides
    @AppScope
    fun providesContext() = ctx

    @Provides
    @AppScope
    fun providesRetrofit(): Retrofit =
        Retrofit.Builder().baseUrl("http://api.football-data.org")
            .client(OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .callTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()

    @Provides
    @AppScope
    fun providesLeagueService(retrofit: Retrofit) =
        retrofit.create(LeagueService::class.java)

    @Provides
    @AppScope
    fun providesLeagueDatabase(ctx: Context): MatchesDao =
       Room.databaseBuilder(ctx,LeagueDatabase::class.java,"LeagueDB")
           .fallbackToDestructiveMigration()
           .build().matchesDao


}
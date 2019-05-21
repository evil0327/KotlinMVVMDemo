package softocean.app.kotlinmvvm.di

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import softocean.app.kotlinmvvm.repo.ApiRepository

import javax.inject.Singleton

@Module
class RepoModule {

    @Provides
    @Singleton
    fun provideApiRepo(): ApiRepository {
        return ApiRepository()
    }

    @Provides
    @Singleton
    internal fun providesSharedPreferences(application: Application): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(application)
    }
}

package com.enestekin.food2forkkmm.android.di

import android.provider.ContactsContract
import com.enestekin.food2forkkmm.android.BaseApplication
import com.enestekin.food2forkkmm.datasource.cache.*
import com.enestekin.food2forkkmm.domain.util.DatetimeUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun provideRecipeDatabase(context: BaseApplication): RecipeDatabase{
        return  RecipeDatabaseFactory(driverFactory = DriverFactory(context)).createDatabase()
    }

    @Singleton
    @Provides
    fun provideRecipeCache(
        recipeDatabase: RecipeDatabase
    ): RecipeCache {
        return RecipeCacheImpl(
            recipeDatabase = recipeDatabase,
            datetimeUtil =  DatetimeUtil()
        )
    }

}
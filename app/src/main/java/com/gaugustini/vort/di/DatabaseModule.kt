package com.gaugustini.vort.di

import android.content.Context
import androidx.room.Room
import com.gaugustini.vort.database.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context,
        typeConverter: TypeConverter
    ): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext, AppDatabase::class.java, "data.db"
        )
            .fallbackToDestructiveMigration()
            .createFromAsset("data.db")
            .addTypeConverter(typeConverter)
            .build()
    }

    @Provides
    @Singleton
    fun provideTypeConverter(): TypeConverter {
        return TypeConverter()
    }

    @Singleton
    @Provides
    fun provideArmorPieceDao(appDatabase: AppDatabase): ArmorPieceDao {
        return appDatabase.armorPieceDao()
    }

    @Singleton
    @Provides
    fun provideDecorationDao(appDatabase: AppDatabase): DecorationDao {
        return appDatabase.decorationDao()
    }

    @Singleton
    @Provides
    fun provideSkillDao(appDatabase: AppDatabase): SkillDao {
        return appDatabase.skillDao()
    }

    @Singleton
    @Provides
    fun provideLanguageDao(appDatabase: AppDatabase): LanguageDao {
        return appDatabase.languageDao()
    }

    @Singleton
    @Provides
    fun provideResultDao(appDatabase: AppDatabase): ResultDao {
        return appDatabase.resultDao()
    }

}

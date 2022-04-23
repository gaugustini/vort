package com.gaugustini.vort.di

import com.gaugustini.vort.database.*
import com.gaugustini.vort.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideArmorPieceRepository(armorPieceDao: ArmorPieceDao): ArmorPieceRepository {
        return ArmorPieceRepository(armorPieceDao)
    }

    @Singleton
    @Provides
    fun provideDecorationRepository(decorationDao: DecorationDao): DecorationRepository {
        return DecorationRepository(decorationDao)
    }

    @Singleton
    @Provides
    fun provideSkillRepository(skillDao: SkillDao): SkillRepository {
        return SkillRepository(skillDao)
    }

    @Singleton
    @Provides
    fun provideLanguageRepository(languageDao: LanguageDao): LanguageRepository {
        return LanguageRepository(languageDao)
    }

    @Singleton
    @Provides
    fun provideResultRepository(result: ResultDao): ResultRepository {
        return ResultRepository(result)
    }

}

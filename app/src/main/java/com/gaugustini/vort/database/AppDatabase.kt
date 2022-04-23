package com.gaugustini.vort.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gaugustini.vort.model.*

@Database(
    entities = [
        Head::class, Body::class, Arms::class, Waist::class, Legs::class,
        Decoration::class, Skill::class, Language::class, Result::class
    ],
    version = 1,
    exportSchema = true,
)
@TypeConverters(value = [TypeConverter::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun armorPieceDao(): ArmorPieceDao

    abstract fun decorationDao(): DecorationDao

    abstract fun skillDao(): SkillDao

    abstract fun languageDao(): LanguageDao

    abstract fun resultDao(): ResultDao

}

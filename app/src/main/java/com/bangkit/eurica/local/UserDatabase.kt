package com.bangkit.eurica.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [SavedList::class],
    version = 1
)
abstract class UserDatabase : RoomDatabase() {
    companion object {
        var INSTANCE : UserDatabase? = null
        fun getDatabase(context: Context): UserDatabase? {
            if (INSTANCE == null) {
                synchronized(UserDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        "saved list database"
                    ).allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }
    }

    abstract fun savedListDao(): SavedListDao
}
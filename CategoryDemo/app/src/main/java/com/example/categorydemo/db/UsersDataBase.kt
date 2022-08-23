package com.example.thought_leadership.data.room_db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [
    User::class,
    SavedContent::class,
    LikedContent::class,
    Category::class,
    SavedContentCategory::class

], version = 1)
abstract class UsersDataBase: RoomDatabase() {



    abstract val userDao: UsersDao

    companion object {
        // @Volatile

        val provisionalUserName = "089471037"

        private var INSTANCE: UsersDataBase? = null

        fun getInstance(context: Context): UsersDataBase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    UsersDataBase::class.java,
                    "someuser26_db"
                ).allowMainThreadQueries().build().also {
                    INSTANCE = it
                }
            }
        }


    }


}
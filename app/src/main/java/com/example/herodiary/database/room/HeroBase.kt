package com.example.herodiary.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.herodiary.database.room.dao.TaskDao
import com.example.herodiary.database.room.dao.UserDao
import com.example.herodiary.database.room.models.TaskRoomModel
import com.example.herodiary.database.room.models.UserRoomModel

@Database(entities = [
    UserRoomModel::class,
    TaskRoomModel::class
                     ], version = 1)
abstract class HeroBase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
    abstract fun getTaskDao(): TaskDao
    companion object {
        fun getDatabase(context: Context): HeroBase {
            return Room.databaseBuilder(
                context.applicationContext,
                HeroBase::class.java,
                "HeroDiary.db"
            ).build()
        }
    }
}